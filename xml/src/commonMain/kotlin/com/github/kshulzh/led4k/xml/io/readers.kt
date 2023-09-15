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

val escaped = mapOf(
    't' to '\t',
    '"' to '"',

    )

fun Reader.readXmlDocument(): XmlDocument {
    var header = readXmlHeader() ?: Tag("xml", attributes = mutableMapOf("version" to "1.0", "encoding" to "UTF-8"))
    var tag = readXmlTag()
    return XmlDocument(tag, header.attributes["version"] ?: "1.0", header.attributes["encoding"] ?: "UTF-8")
}

fun Reader.readXmlTag(): Tag? {
    val position = position
    readWS()
    if (read() != '<') {
        this.position = position
        return null
    }
    readWS()
    var name = readName() ?: run {
        this.position = position
        return null
    }
    readWS()
    var attributes = mutableMapOf<String, String?>()
    var attribute = readXmlAttribute()
    while (attribute != null) {
        attributes.put(attribute.first, attribute.second)
        readWS()
        attribute = readXmlAttribute()
    }
    readWS()
    var c = read()
    if (c == '/') {
        readWS()
        c = read()
        if (c == '>') {
            return Tag(name, attributes = attributes)
        }
    }
    if (c != '>') {
        this.position = position
        return null
    }
    var children = mutableListOf<Any>()
    readWS()
    var child = readPlainText() ?: readXmlTag()
    while (child != null) {
        children.add(child)
        readWS()
        child = readPlainText() ?: readXmlTag()
    }
    c = read()
    if (c != '<') {
        this.position = position
        return null
    }
    readWS()
    c = read()
    if (c != '/') {
        this.position = position
        return null
    }
    readWS()
    var closeName = readName() ?: name
    if (closeName != name) {
        this.position = position
        return null
    }
    readWS()
    c = read()
    if (c != '>') {
        this.position = position
        return null
    }

    return Tag(name, null, attributes, children)

}

fun Reader.readWS() {
    while (read() in arrayOf(' ', '\t', '\n', '\r'));
    position--
}

fun Reader.readName(): String? {
    val position = position
    val stringBuilder = StringBuilder()
    var c = read()
    while (c.isLetterOrDigit() or (c == ':')) {
        stringBuilder.append(c)
        c = read()
    }
    if (stringBuilder.isEmpty()) {
        this.position = position
        return null
    }
    this.position--
    return stringBuilder.toString()
}

fun Reader.readString(): String? {
    val position = position
    readWS()
    if (read() != '"') {
        this.position = position
        return null
    }
    val stringBuilder = StringBuilder()
    var c = read()
    while (c != '"') {
        if (c == '\\') {
            stringBuilder.append(escaped[read()])
        } else {
            stringBuilder.append(c)
        }
        c = read()
    }
    return stringBuilder.toString()
}

fun Reader.readXmlAttribute(): Pair<String, String?>? {
    val position = position
    val name = readName() ?: return null
    val position1 = position
    readWS()
    val c = read()
    if (c != '=') {
        this.position = position1
        return name to null
    }
    val value = readString() ?: run {
        this.position = position
        return null
    }
    return name to value
}

fun Reader.readPlainText(): String? {
    val position = position
    val stringBuilder = StringBuilder()
    var c = read()
    while (c != '<') {
        stringBuilder.append(c)
        c = read()
    }

    if (stringBuilder.isEmpty()) {
        this.position = position
        return null
    }
    this.position--
    return stringBuilder.toString()
}

fun Reader.readXmlHeader(): Tag? {
    val position = position
    readWS()
    if (read() != '<') {
        this.position = position
        return null
    }
    readWS()
    if (read() != '?') {
        this.position = position
        return null
    }
    readWS()
    var name = readName() ?: run {
        this.position = position
        return null
    }
    readWS()
    var attributes = mutableMapOf<String, String?>()
    var attribute = readXmlAttribute()
    while (attribute != null) {
        attributes.put(attribute.first, attribute.second)
        readWS()
        attribute = readXmlAttribute()
    }
    readWS()
    if (read() != '?') {
        this.position = position
        return null
    }
    readWS()
    if (read() != '>') {
        this.position = position
        return null
    }
    return Tag(name, attributes = attributes)
}
