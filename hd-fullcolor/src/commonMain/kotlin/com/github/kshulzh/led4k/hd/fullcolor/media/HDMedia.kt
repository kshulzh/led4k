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

import com.github.kshulzh.led4k.common.media.Media
import com.github.kshulzh.led4k.media.InputStream

interface HDMedia : Media {
    companion object

    val inputStream: InputStream
    val md5: String
    val extension: String
    val fileName get() = "${md5}.$extension"
    val size: Long get() = inputStream.available().toLong()
}