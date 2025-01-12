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

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

actual class CanvasMedia
actual constructor(actual val width: Int, actual val height: Int, override val extension: String) : ImageMedia {
    override val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    actual fun drawImage(image: Image, x: Int, y: Int, width: Int, height: Int) {
        val canvas = this.image.createGraphics()
        canvas.drawImage(image, x, y, width, height, null)
        canvas.dispose()
    }

    override fun write(outputStream: OutputStream) {
        ImageIO.write(image, extension, outputStream)
    }

    actual fun setPixel(color: Int, x: Int, y: Int) {
        image.setRGB(x, y, color)
    }
}