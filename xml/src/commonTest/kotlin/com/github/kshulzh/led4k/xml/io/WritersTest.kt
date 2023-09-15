package com.github.kshulzh.led4k.xml.io

import com.github.kshulzh.led4k.xml.model.Tag
import com.github.kshulzh.led4k.xml.model.XmlDocument
import kotlin.test.Test
import kotlin.test.assertTrue

class WritersTest {
    @Test
    fun shouldWriteTag() {
        val tag = Tag("HELLO")
        val charArray = CharArray(10000) { i -> (0.toChar()) }
        CharArrayWriter(charArray).writeXmlTag(tag)
        assertTrue {
            charArray.concatToString().contains("<HELLO/>")
        }
    }

    @Test
    fun shouldWriteDocument() {
        val document = XmlDocument(Tag("HELLO", children = mutableListOf("1234")))
        val charArray = CharArray(10000) { i -> (0.toChar()) }
        CharArrayWriter(charArray).writeXmlDocument(document)
        assertTrue {
            charArray.concatToString().contains("<HELLO>")
        }
    }
}