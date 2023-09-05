package com.github.kshulzh.led4k.common.model.areas

import com.github.kshulzh.led4k.common.exception.AreaTypeNotFoundException
import com.github.kshulzh.led4k.common.exception.RecognizeException
import kotlin.reflect.KFunction1

object AreaFactory {
    var constructors = mutableMapOf<String, KFunction1<Map<Any, Any?>, Area>>(
        TextArea::class.simpleName!! to ::TextArea,
        ImageArea::class.simpleName!! to ::ImageArea,
        ContainerArea::class.simpleName!! to ::ContainerArea
    )


    fun getAreaByProperties(properties: Map<Any, Any?>): Area {
        return constructors[properties.getString("type", "undefined")]?.invoke(properties)
            ?: throw AreaTypeNotFoundException(properties.getString("type", "undefined"))
    }

    fun getArea(obj: Any): Area {
        return if (obj is Map<*, *>) {
            return getAreaByProperties(obj as Map<Any, Any?>)
        } else {
            throw RecognizeException(obj)
        }
    }
}