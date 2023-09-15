/*
 *   Copyright (c) 2023. Kyrylo Shulzhenko
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.github.kshulzh.led4k.common.io

actual fun File.of(path: String): File {
    return FileJvm(path)
}

class FileJvm(override var path: String) : File {
    override var name: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var size: Long
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun open() {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    override fun write(byteArray: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun read(byteArray: ByteArray) {
        TODO("Not yet implemented")
    }
}