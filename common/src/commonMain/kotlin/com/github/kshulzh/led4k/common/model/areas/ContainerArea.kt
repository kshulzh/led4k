package com.github.kshulzh.led4k.common.model.areas

import com.github.kshulzh.led4k.common.exception.RecognizeException

class ContainerArea(properties: Map<Any, Any?>) : Area(properties) {
    var areas: List<Area>

    init {
        //var rawAreas = properties["areas"] as? Collection<Map<Any, Any?>>
        areas = properties.getArray("areas").filter {
            it == null
        }.map {
            if (it is Map<*, *>) {
                AreaFactory.getArea(it)
            } else {
                throw RecognizeException(it)
            }
        }
    }
}


