/*
 * Copyright (c) 2025. Kirill Shulzhenko
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

package com.github.kshulzh.led4k.media.network

import com.github.kshulzh.led4k.media.InputStream
import com.github.kshulzh.led4k.media.OutputStream
import java.net.Socket

actual class TCPConnection actual constructor(val address: String, val port: Int) {
    lateinit var socket: Socket
    lateinit var inputStream: InputStream
    lateinit var outputStream: OutputStream
    actual fun open() {
        socket = Socket(address, port)
        inputStream = socket.inputStream
        outputStream = socket.outputStream
    }

    actual fun close() {
        inputStream.close()
        outputStream.close()
        socket.close()
    }

    actual fun write(bytes: ByteArray) {
        outputStream.write(bytes)
    }

    actual fun read(bytes: ByteArray, off: Int, len: Int) {
        inputStream.read(bytes, off, len)
    }

}