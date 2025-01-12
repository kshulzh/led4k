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

package com.github.kshulzh.led4k.hd.fullcolor.controller

import com.github.kshulzh.led4k.common.controller.NetworkController
import com.github.kshulzh.led4k.common.program.Program
import com.github.kshulzh.led4k.common.program.ProgramBuilder
import com.github.kshulzh.led4k.hd.fullcolor.builder.HDBuilderContext
import com.github.kshulzh.led4k.hd.fullcolor.media.ByteArrayHDMedia
import com.github.kshulzh.led4k.hd.fullcolor.model.HDControllerPlugin
import com.github.kshulzh.led4k.hd.fullcolor.model.asNode
import com.github.kshulzh.led4k.hd.fullcolor.program.HDListProgram
import com.github.kshulzh.led4k.hd.fullcolor.program.HDSingleProgram
import com.github.kshulzh.led4k.media.network.TCPConnection
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML

class HDController(
    override var address: String,
    var port: Int = 9527,
    val width: Int = 128,
    val height: Int = 128,
    val appVersion: String = "7.10.2.0",
    val model: String
) : NetworkController {
    @Suppress("UNCHECKED_CAST")
    override fun upload(program: Program) {
        val context = HDBuilderContext()
        val listProgram: HDListProgram = when (program) {
            is HDListProgram -> program
            is HDSingleProgram -> HDListProgram(mutableListOf(program))
            is ProgramBuilder<*, *> -> (program as ProgramBuilder<HDListProgram, HDBuilderContext>).build(context)
            else -> throw RuntimeException("Unsupported program $program")
        }
        val hdControllerPlugin = HDControllerPlugin(
            deviceModel = model,
            width = width,
            height = height,
            appVersion = appVersion,
            nodes = listProgram.programs
                .filterIsInstance<HDSingleProgram>()
                .map { it.hdOrdinaryScenePlugin }
                .toMutableList()
        )

        val main = ByteArrayHDMedia(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + XML {
            indentString = "    "
        }.encodeToString(hdControllerPlugin.asNode()) + "\n").replace("\n", "\r\n").toByteArray(), "boo")

        val device = HDDevice(TCPConnection(address, port))
        device.sendFirstMessage()
        Thread.sleep(10)
        //device.send0730()
        //Thread.sleep(10);

        device.sendInfo("admin,8631956c-1a5e-401f-9d2e-ebad3ebdf298,2021/02/22 17:49:24\u0000")
        //device.sendInfo("admin,e5fe51f8-cdb2-403b-bc61-c086ab558bd0,2024/12/23 21:05:29\u0000")
        Thread.sleep(10)
        device.send040A()
        Thread.sleep(10)
        device.upload(main, *(listProgram.mediaElements.toTypedArray()))
        device.sendEnd()
        device.close()
    }
}