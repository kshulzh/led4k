package com.github.kshulzh.led4k.common.model

import com.github.kshulzh.led4k.common.model.areas.getLong

open class Point(val properties: Map<Any, Any?>) {
    var x: Long
    var y: Long

    init {
        x = properties.getLong("x")
        y = properties.getLong("y")
    }
}
