/*
 * Copyright (c) 2024-2026. Kirill Shulzhenko
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

package io.github.kshulzh.led4k.hd.fullcolor.controller

import io.github.kshulzh.led4k.common.controller.NetworkController
import io.github.kshulzh.led4k.common.program.Program
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.Datagram
import io.ktor.network.sockets.InetSocketAddress
import io.ktor.network.sockets.aSocket
import io.ktor.utils.io.core.ByteReadPacket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.io.files.Path
import kotlinx.io.readByteArray

/**
 * A controller for managing high-definition (HD) devices by extending the `NetworkController` interface.
 * Handles the transmission of programs to a target HD device over a network using the specified
 * address and port. The controller also manages internal transformations and session-based
 * communication with the target device.
 *
 * @property address The network address of the HD device.
 * @property port The network port used to establish the connection. Defaults to 9527.
 * @property width The width of the display or resolution of the target HD device. Defaults to 128.
 * @property height The height of the display or resolution of the target HD device. Defaults to 128.
 * @property appVersion The version of the application or protocol used for communication. Defaults to "7.10.2.0".
 * @property tmpPath The temporary directory path used for storing intermediate data during transformations.
 * @property model The specific HD device model that is being targeted for operations.
 */
class HDController(
    override var address: String,
    var port: Int = 9527,
    val width: Int = 128,
    val height: Int = 128,
    val appVersion: String = "7.10.2.0",
    val tmpPath: String = "tmp",
    val model: String
) : NetworkController {

    /**
     * Uploads the provided program to a remote device using a predefined session.
     *
     * This method processes the given program using a transformer, prepares
     * it for transfer, and initiates an upload session. During the session,
     * a series of communication steps are performed to ensure the data is
     * successfully transmitted to the remote device.
     *
     * @param program The program to be uploaded. Must implement the [Program] interface.
     */
    override suspend fun upload(program: Program) {
        val transformer = ProgramTransformer(Path(tmpPath), width, height, appVersion, model)
        transformer.process(program)

        withSession{
            it.sendFirstMessage()
            it.send0730()
            it.sendUserInfo("admin,9aae6804-3648-4920-a05b-17b024b07cb2,2026/04/01 22:29:13\u0000")
            it.sendEndInput()
            it.send040a()
            it.upload(transformer.main, *transformer.files.values.toTypedArray())
            it.sendEnd()
            it.sendClose()
        }
    }

    /**
     * Establishes a TCP connection to the specified address and port, creating a session for communication
     * with the HD device.
     *
     * @return an instance of [HDDeviceSession], which represents the session for interacting with the device.
     */
    private suspend inline fun withSession(block: (HDDeviceSession) -> Unit) {
        val selector = SelectorManager(Dispatchers.IO)
        val socket = aSocket(selector).tcp().connect(InetSocketAddress(address, port)) {
            sendBufferSize = 9216
            receiveBufferSize = 9216
            this.keepAlive = false
        }

        HDDeviceSession(socket).use(block)
    }

    /**
     * Sends a UDP request to the specified remote address and returns the received response.
     *
     * @param bytes the byte array representing the data to be sent in the UDP request.
     * @return a byte array containing the response data received from the remote server.
     */
    private suspend fun sampleRequest(bytes: ByteArray) : ByteArray {
        val selector = SelectorManager(Dispatchers.IO)
        val remoteAddress = InetSocketAddress(address, port)
        return aSocket(selector).udp().connect(remoteAddress).use {
            it.send(Datagram(ByteReadPacket(bytes), remoteAddress))
            val datagram = it.receive()
            datagram.packet.readByteArray()
        }
    }
}