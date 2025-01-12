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

import javax.imageio.ImageIO

actual object ImageIO {
    actual fun read(inputStream: InputStream): Image {
        return ImageIO.read(inputStream)
    }

    actual fun write(image: Image, format: String, outputStream: OutputStream) {
        ImageIO.write(image, format, outputStream)
    }
}