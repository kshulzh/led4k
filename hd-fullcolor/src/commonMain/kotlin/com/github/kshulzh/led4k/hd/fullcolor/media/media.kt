/*
 * Copyright (c) 2024-2025. Kirill Shulzhenko
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

package com.github.kshulzh.led4k.hd.fullcolor.media

import com.github.kshulzh.led4k.media.Files
import com.github.kshulzh.led4k.media.GraphicMedia
import com.github.kshulzh.led4k.media.Paths
import com.github.kshulzh.led4k.media.delete
import com.github.kshulzh.led4k.media.inputStream
import com.github.kshulzh.led4k.media.outputStream

fun HDMedia.Companion.of(uri: String) = ByteArrayHDMedia(Paths.get(uri).inputStream, uri.split(".").last())

//todo optimize this
fun HDMedia.Companion.ofMedia(graphicMedia: GraphicMedia): HDMedia {
    val tmp = Files.createTempFile(suffix = ".${graphicMedia.extension}")
    graphicMedia.write(tmp.outputStream)

    return ByteArrayHDMedia(tmp.inputStream, graphicMedia.extension).also {
        tmp.delete()
    }
}