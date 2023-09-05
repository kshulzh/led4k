package com.github.kshulzh.led4k.common.model.areas

class TextArea(properties: Map<Any, Any?>) : Area(properties) {
    var text: String

    init {
        text = properties["text"] as? String ?: ""
    }
}