package com.github.kshulzh.led4k.xml.io

import kotlin.test.Test
import kotlin.test.assertEquals

class ReadersTest {
    @Test
    fun shouldReadTag() {
        val rawXmlTag = "  <  hello a=\"999\" >  < a  / >    9090<  / hello> "
        val tag = CharArrayReader(rawXmlTag.toCharArray()).readTag()
        assertEquals("hello", tag?.name)
        assertEquals("999", tag?.attributes!!["a"]!!)
    }

    @Test
    fun shouldReadDocument() {
        val rawXmlDocument = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<  hello a=\"999\" >  < a  / >    9090<  / hello> "
        val tag = CharArrayReader(rawXmlDocument.toCharArray()).readDocument()
        assertEquals("1.0", tag.version)
        assertEquals("hello", tag.tag?.name)
        assertEquals("999", tag.tag?.attributes!!["a"]!!)
    }
}