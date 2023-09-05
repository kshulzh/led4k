package com.github.kshulzh.led4k.common.model.areas

fun Map<*, *>.getLong(key: String, default: Long = 0L): Long =
    (this[key] as? Number)?.toLong() ?: (this[key] as? String)?.toLong() ?: default

fun Map<*, *>.getString(key: String, default: String = ""): String {
    var a = this[key] as? String ?: default
    return a
}

fun Map<*, *>.getArray(key: String): Collection<*> {
    val value = this[key] ?: mutableListOf<Any>()
    return when (value) {
        is Collection<*> -> {
            return value
        }

        is Map<*, *> -> {
            return mutableListOf(value)
        }

        else -> {
            return mutableListOf(mutableMapOf("value" to value))
        }
    }
}
