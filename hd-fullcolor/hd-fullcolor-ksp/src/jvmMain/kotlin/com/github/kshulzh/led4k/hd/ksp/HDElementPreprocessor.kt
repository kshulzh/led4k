/*
 *   Copyright (c) 2023. Kyrylo Shulzhenko
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.github.kshulzh.led4k.hd.ksp

import com.github.kshulzh.led4k.hd.ksp.annotations.HDAttribute
import com.github.kshulzh.led4k.hd.ksp.annotations.HDElement
import com.github.kshulzh.led4k.hd.ksp.annotations.HDList
import com.github.kshulzh.led4k.hd.ksp.annotations.HDNodes
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import java.io.OutputStream

class HDElementPreprocessor(
    val codeGenerator: CodeGenerator,
    val options: Map<String, String>
) : SymbolProcessor {
    lateinit var file: OutputStream
    lateinit var resolver: Resolver
    var invoked = false

    fun OutputStream.write(s: String) {
        write(s.encodeToByteArray())
        write('\n'.code)
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) {
            return emptyList()
        }
        this.resolver = resolver
        file =
            codeGenerator.createNewFile(Dependencies.ALL_FILES, "com.github.kshulzh.led4k.hd.ksp", "mappers", "kt")
        file.write("package com.github.kshulzh.led4k.hd.ksp\n")
        file.write("import com.github.kshulzh.led4k.xml.model.Tag\n")
        val files = resolver.getAllFiles()
        val elements = resolver.getSymbolsWithAnnotation(HDElement::class.java.name)
        file.write("""
            fun mapNode(node: com.github.kshulzh.led4k.hd.model.elements.HD_Node) : Tag {
              return when(node.name) {
              ${
            elements.filter {
                it is KSClassDeclaration
            }.map {
                val clazz = it as KSClassDeclaration
                "\"${clazz.simpleName.getShortName()}\" -> (node as ${clazz.packageName.asString()}.${clazz.simpleName.getShortName()}).toXml()"

            }.run {
                if (this.count() > 0) {
                    this.reduce { acc, s -> "$acc \n$s" }
                } else {
                    ""
                }
            }
        }
                else -> throw RuntimeException()
              }
            }
        """.trimIndent()
        )
        elements.forEach {
            val clazz = it as KSClassDeclaration
            processHDElement(clazz)

        }
        file.flush()
        file.close()

        invoked = true
        return emptyList()
    }

    @OptIn(KspExperimental::class)
    fun processHDElement(clazz: KSClassDeclaration) {
        file.write(
            FunSpec.builder("toXml")
                .receiver(ClassName(clazz.packageName.asString(), clazz.simpleName.asString()))
                .addCode("val tag = Tag(\"Node\", attributes = mutableMapOf<String, String?>(\"Name\" to \"${clazz.simpleName.asString()}\",\"Level\" to this.level))\n")
                .addCodeBlocks(
                    if (clazz.getAllProperties().count() > 0) {
                        clazz.getAllProperties().map {
                            if (it.isAnnotationPresent(HDAttribute::class)) {
                                processHDAttribute(clazz, it)
                            } else if (it.isAnnotationPresent(HDList::class)) {
                                processHDList(clazz, it)
                            } else if (it.isAnnotationPresent(HDNodes::class)) {
                                processHDNode(clazz, it)
                            } else {
                                CodeBlock.of("")
                            }
                        }.toList()
                    } else {
                        emptyList()
                    }
                )
                .addCode("return tag\n")
                .returns(ClassName("com.github.kshulzh.led4k.xml.model", "Tag"), CodeBlock.of("return tag"))
                .build().toString()
        )
    }

    @OptIn(KspExperimental::class)
    fun processHDAttribute(clazz: KSClassDeclaration, property: KSPropertyDeclaration): CodeBlock {
        val annotation = property.getAnnotationsByType(HDAttribute::class).first()
        return CodeBlock.of("tag.children.add(Tag(\"Attribute\", children = mutableListOf<Any>(this.${property.simpleName.getShortName()} ?: \"${annotation.default}\"), attributes = mutableMapOf<String, String?>(\"Name\" to \"${annotation.name}\")))\n")
    }

    @OptIn(KspExperimental::class)
    fun processHDList(clazz: KSClassDeclaration, property: KSPropertyDeclaration): CodeBlock {
        val annotation = property.getAnnotationsByType(HDList::class).first()
        val propertyName = property.simpleName.getShortName()
        val type = property.type.resolve().arguments.first().type!!.resolve().declaration.simpleName

        val listClass = resolver.getSymbolsWithAnnotation(HDList::class.java.name).filter { it is KSClassDeclaration }
            .map { it as KSClassDeclaration }.filter { it.simpleName.getShortName() == type.getShortName() }.first()
        return CodeBlock.of("""
            if((${propertyName} == null) or (${propertyName}.isEmpty())){
                tag.children.add(Tag("List", attributes = mutableMapOf<String, String?>("Name" to "${annotation.name}","Index" to "-1")))
            } else {
                tag.children.add(Tag("List", children = ${propertyName}.map {
                    Tag("ListItem", attributes = mutableMapOf<String, String?>(${
            if (listClass.getAllProperties().count() > 0) {
                listClass.getAllProperties().filter {
                    it.isAnnotationPresent(HDAttribute::class)
                }.map {
                    val attributeAnnotation = it.getAnnotationsByType(HDAttribute::class).first()
                    "\"${attributeAnnotation.name}\" to it.${it.simpleName.getShortName()} ${if (attributeAnnotation.default.isNotEmpty()) "?: ${attributeAnnotation.default}" else ""}"
                }.reduce { acc, s -> "$acc, $s" }
            } else {
                ""
            }
        }       ))
                }.toMutableList(), attributes = mutableMapOf<String, String?>("Name" to "${annotation.name}","Index" to "0")))
                
            }
            
        """.trimIndent())
    }

    fun processHDNode(clazz: KSClassDeclaration, property: KSPropertyDeclaration): CodeBlock {
        val propertyName = property.simpleName.getShortName()
        return CodeBlock.of(
            """
                $propertyName.forEach {
                    it.level = (this.level.toLong()+1).toString()
                    tag.children.add(mapNode(it))
                }
                
        """.trimIndent()
        )
    }

}

fun FunSpec.Builder.addCodeBlocks(codeBlocks: List<CodeBlock>): FunSpec.Builder {
    codeBlocks.forEach {
        this.addCode(it)
    }
    return this
}

class HDProcessorProvider : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment
    ): SymbolProcessor {
        return HDElementPreprocessor(environment.codeGenerator, environment.options)
    }
}