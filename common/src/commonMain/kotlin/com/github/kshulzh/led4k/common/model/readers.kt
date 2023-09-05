package com.github.kshulzh.led4k.common.model

import com.github.kshulzh.led4k.common.model.areas.Area
import com.github.kshulzh.led4k.common.model.areas.AreaFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

fun readAreaFromJsonString(json: String): Area {
    return readAreaFromJson(Json.parseToJsonElement(json))
}

fun readAreaFromJson(json: JsonElement): Area {
    return AreaFactory.getArea(mapJson(json)!!)
}

fun mapJson(json: JsonElement): Any? {
    return if (json is JsonObject) {
        return json.toMap().mapValues {
            mapJson(it.value)
        }
    } else if (json is JsonArray) {
        return json.map {
            mapJson(it)
        }
    } else if (json.jsonPrimitive.isString) {
        return json.jsonPrimitive.content
    } else {
        //todo
        return json.jsonPrimitive.content.toLongOrNull() ?: json.jsonPrimitive.content.toBooleanStrictOrNull()
    }
}