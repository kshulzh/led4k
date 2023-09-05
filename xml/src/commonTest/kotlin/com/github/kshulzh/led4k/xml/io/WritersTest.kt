package com.github.kshulzh.led4k.xml.io

import com.github.kshulzh.led4k.xml.model.Document
import com.github.kshulzh.led4k.xml.model.Tag
import kotlin.test.Test
import kotlin.test.assertTrue

class WritersTest {
    @Test
    fun shouldWriteTag() {
        val tag = Tag("HELLO")
        val charArray = CharArray(10000) { i -> (0.toChar()) }
        CharArrayWriter(charArray).writeTag(tag)
        assertTrue {
            charArray.concatToString().contains("<HELLO/>")
        }
    }

    @Test
    fun shouldWriteDocument() {
        val document = Document(Tag("HELLO", children = mutableListOf("1234")))
        val charArray = CharArray(10000) { i -> (0.toChar()) }
        CharArrayWriter(charArray).writeDocument(document)
        assertTrue {
            charArray.concatToString().contains("<HELLO>")
        }
    }
}