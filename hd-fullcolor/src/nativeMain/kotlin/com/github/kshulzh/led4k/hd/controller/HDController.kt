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

package com.github.kshulzh.led4k.hd.controller

actual fun HDController.of(ip: String): HDController {
    return HDControllerNative(ip)
}

class HDControllerNative(override var ip: String) : HDController(ip) {
    override fun write(bytes: ByteArray, type: Int) {
        TODO("Not yet implemented")
    }

    override fun read(): ByteArray {
        TODO("Not yet implemented")
    }
}