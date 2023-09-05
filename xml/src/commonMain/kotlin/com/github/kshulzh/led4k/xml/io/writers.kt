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

package com.github.kshulzh.led4k.xml.io

import com.github.kshulzh.led4k.xml.model.Document
import com.github.kshulzh.led4k.xml.model.Tag

fun Writer.writeDocument(document: Document) {
    writeHeader(document)
    document.tag?.apply {
        writeTag(this)
    }
}

fun Writer.writeHeader(document: Document) {
    writeLine("<?xml version=\"${document.version}\" encoding=\"${document.encoding}\"?>")
}

fun Writer.writeTag(tag: Tag) {
    if (tag.children.isEmpty()) {
        writeLine("<${tag.name}${mapAttributesToString(tag.attributes)}/>")
    } else {
        if (tag.children.find { it is Tag } != null) {
            writeLine("<${tag.name}${mapAttributesToString(tag.attributes)}>")
            setIndent(getIndent() + 1)
            tag.children.forEach {
                if (it is Tag) {
                    writeTag(it)
                } else {
                    writeLine(it.toString())
                }
            }
            setIndent(getIndent() - 1)
            writeLine("</${tag.name}>")
        } else {
            writeLine(
                "<${tag.name}${mapAttributesToString(tag.attributes)}>${
                    tag.children.map { it.toString() }.reduce { acc, s -> "$acc$s" }
                }</${tag.name}>"
            )
        }
    }
}

fun Writer.setIndentString(s: String) {
    properties["indentString"] = s
}

fun Writer.getIndentString() = properties["indentString"] as? String ?: " "

fun Writer.setIndent(n: Int) {
    properties["indent"] = n
}

fun Writer.getIndent() = properties["indent"] as? Int ?: 0


fun Writer.writeLine(s: String) {
    write("${getIndentString().repeat(getIndent())}$s\n")
}

fun Writer.writeString(s: String) {
    //todo escape chars
    write(escapeString(s))
}

fun escapeString(s: String) = "\"${s}\""

fun mapAttributesToString(attributes: Map<String, String?>): String {
    return if (attributes.isEmpty()) {
        ""
    } else {
        " ${
            attributes.map { "${it.key}${if (it.value != null) "=${escapeString(it.value.toString())}" else ""}" }
                .reduce { acc, s -> "$acc $s" }
        }"
    }
}