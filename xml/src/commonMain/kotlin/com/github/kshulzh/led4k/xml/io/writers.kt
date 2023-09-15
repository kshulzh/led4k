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

import com.github.kshulzh.led4k.xml.model.Tag
import com.github.kshulzh.led4k.xml.model.XmlDocument

fun Writer.writeXmlDocument(document: XmlDocument) {
    writeXmlHeader(document)
    document.tag?.apply {
        writeXmlTag(this)
    }
}

fun Writer.writeXmlHeader(document: XmlDocument) {
    writeLine("<?xml version=\"${document.version}\" encoding=\"${document.encoding}\"?>")
}

fun Writer.writeXmlTag(tag: Tag) {
    if (tag.children.isEmpty()) {
        writeLine("<${tag.name}${encodeXmlAttributesToString(tag.attributes)}/>")
    } else {
        if (tag.children.find { it is Tag } != null) {
            writeLine("<${tag.name}${encodeXmlAttributesToString(tag.attributes)}>")
            indent++
            tag.children.forEach {
                if (it is Tag) {
                    writeXmlTag(it)
                } else {
                    writeLine(it.toString())
                }
            }
            indent--
            writeLine("</${tag.name}>")
        } else {
            writeLine(
                "<${tag.name}${encodeXmlAttributesToString(tag.attributes)}>${
                    tag.children.map { it.toString() }.reduce { acc, s -> "$acc$s" }
                }</${tag.name}>"
            )
        }
    }
}

var Writer.indentString: String
    set(value) {
        properties["indentString"] = value
    }
    get() = properties["indentString"] as? String ?: " "
var Writer.indent: Int
    set(value) {
        properties["indent"] = value
    }
    get() = properties["indent"] as? Int ?: 0


fun Writer.writeLine(s: String) {
    write("${indentString.repeat(indent)}$s\n")
}

fun Writer.writeString(s: String) {
    //todo escape chars
    write(escapeString(s))
}

fun escapeString(s: String) = "\"${s}\""

fun encodeXmlAttributesToString(attributes: Map<String, String?>): String {
    return if (attributes.isEmpty()) {
        ""
    } else {
        " ${
            attributes.map { "${it.key}${if (it.value != null) "=${escapeString(it.value.toString())}" else ""}" }
                .reduce { acc, s -> "$acc $s" }
        }"
    }
}