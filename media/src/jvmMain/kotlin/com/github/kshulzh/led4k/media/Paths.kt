/*
 * Copyright (c) 2024. Kirill Shulzhenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.kshulzh.led4k.media

import com.github.kshulzh.led4k.common.utils.md5
import java.net.URI
import java.net.URL
import java.nio.file.Files
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


actual object Paths {
    val cache = mutableMapOf<String, java.nio.file.Path>()
    actual fun get(uri: String): Path {
        val u = URI.create(uri)
        val scheme = uri.split(":")[0]
        return cache[uri] ?: when (scheme) {
            "http" -> http(uri)
            "https" -> http(uri)
            "data" -> data(uri)
            uri -> Path.of(uri)
            else -> Path.of(u)
        }.also {
            cache[uri] = it
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun http(uri: String): java.nio.file.Path {
        val url = URL(uri).openConnection()
        val extension = getExtension(url.contentType) ?: uri.lastIndexOf('.').run {
            if (this != -1) {
                uri.drop(uri.lastIndexOf('.') + 1)
            } else {
                null
            }
        }
        val bytes = url.inputStream.readAllBytes()
        val name = bytes.md5().toHexString() + if (extension == null) "" else ".$extension"
        val path = java.nio.file.Path.of(name)
        Files.write(path, bytes)

        return path
    }

    @OptIn(ExperimentalEncodingApi::class, ExperimentalStdlibApi::class)
    fun data(uri: String): java.nio.file.Path {
        val raw = uri.split(',')
        val metadata = raw[0].drop("data:".length).split(';')
        val extension = getExtension(metadata[0])
        val bytes = when (metadata[1]) {
            "base64" -> Base64.decode(raw[1])
            else -> throw UnsupportedOperationException()
        }

        val name = bytes.md5().toHexString() + if (extension == null) "" else ".$extension"
        val path = java.nio.file.Path.of(name)
        Files.write(path, bytes)

        return path
    }

    fun getExtension(contentType: String): String? {
        return when (contentType) {
            "image/png" -> "png"
            "image/jpeg" -> "jpeg"
            "image/gif" -> "gif"
            "video/mp4" -> "mp4"
            else -> null
        }
    }
}