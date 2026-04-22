/*
 * Copyright (c) 2026. Kirill Shulzhenko
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

import io.github.kshulzh.led4k.common.media.Media
import io.github.kshulzh.led4k.hd.fullcolor.media.HDMedia
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.core.readAvailable
import io.ktor.utils.io.readFully
import io.ktor.utils.io.writeByteArray
import io.ktor.utils.io.writeFully
import kotlinx.io.InternalIoApi
import kotlinx.io.buffered
import kotlin.math.min

/**
 * Represents a session for communication with an HD device over a socket.
 * Provides functionality to interact with the device, including sending and receiving data packets,
 * uploading media, and managing files on the device.
 *
 * This class implements [AutoCloseable] to ensure proper resource management and allows*/
class HDDeviceSession(
    val socket: Socket
) : AutoCloseable {
    /**
     * Companion object for the HDDeviceSession class.
     *
     * Provides constants and potentially static utility methods shared across all instances of HDDeviceSession.
     */
    companion object {
        /**
         * Represents the maximum size, in bytes, for packets transmitted or received
         * within a session. This value is used to enforce constraints on the buffer sizes
         * for network operations, ensuring compliance with protocol standards or device limitations.
         */
        const val MAX_PACKET_SIZE = 9212
    }
    /**
     * The input stream of the socket connection, used for reading data from the connected device.
     *
     * This property provides a read channel that can be utilized to receive incoming data from the
     * socket. It serves as the primary interface for reading communication packets or streamed data
     * related to the session established with the hardware device.
     */
    val input = socket.openReadChannel()
    /**
     * Represents an output channel for sending data through the socket connection.
     * This channel operates with auto-flushing enabled, ensuring that written data
     * is sent immediately without requiring an explicit flush operation.
     *
     * This variable is used within the session to facilitate communication by
     * writing data to the connected remote endpoint.
     */
    val output = socket.openWriteChannel(autoFlush = true)

    /**
     * Reads a packet of data from the connected input stream.
     * The method attempts to read the packet up to five times
     * if the packet type does not match the expected type.
     *
     * @param type The expected type of the packet to be read.
     * @return A `ByteArray` containing the data of the successfully read packet.
     * @throws RuntimeException If the packet cannot be read after five attempts or the connection is closed.
     */
    suspend fun readPacket(type : Int) : ByteArray {
        var attempt = 0
        val buffer = ByteArray(4)
        do {
            input.readFully(buffer)
            val size = (buffer[0].toUByte().toUInt() or (buffer[1].toUByte().toUInt() shl 8)).toInt()
            val actualType = buffer[2].toUByte().toUInt() or (buffer[3].toUByte().toUInt() shl 8)
            if (type != actualType.toInt()) {
                attempt++
                continue
            }
            val buffer2 = ByteArray(size)
            buffer.copyInto(buffer2, 0, 0, 4)
            input.readFully(buffer2, 4, size)
            return buffer2
        } while (attempt < 5)
        throw RuntimeException("Connection closed during execution")
    }

    /**
     * Sends the first initialization message to the connected HD device session.
     *
     * This method writes a predefined "hello" message to the output stream, signaling the
     * initiation of communication with the device. It then awaits a response packet of a specific
     * type to confirm that the initial handshake is complete*/
    suspend fun sendFirstMessage() {
        output.writeByteArray(MessageFactory.messageHello)
        readPacket(MessageFactory.FIRST_MESSAGE + 1)
    }

    /**
     * Sends a pre-defined message (0730) to the connected device and waits for a response packet.
     *
     * The method writes a predefined message (`MessageFactory.message0730`) to the output stream, then
     * waits for a response packet of a specific type (`MessageFactory.M_0730 + 1`). It ensures that the
     * device receives the intended command and that a response is*/
    suspend fun send0730() {
        output.writeByteArray(MessageFactory.message0730)
        readPacket(MessageFactory.M_0730 + 1)
    }

    /**
     * Sends user information to the device.
     *
     * This method constructs a user information message using the provided name,
     * writes it to the output stream, and then waits for a response packet
     * of a specific type.
     *
     * @param name The name of the user to be included in the message. Cannot be null or empty.
     *             This will be used in constructing the message payload*/
    suspend fun sendUserInfo(name: String) {
        output.writeFully(MessageFactory.messageUserInfo(name))
        readPacket(MessageFactory.USER_GUID_TIME + 1)
    }

    /**
     * Sends the final input message to the connected device, indicating the end of the input sequence.
     *
     * This method writes a predefined termination message to the device's output stream via the
     * `MessageFactory.messageEndAuth` data. After sending the message, it waits for and processes
     * the corresponding response packet, which is expected to match the message type specified
     * by `MessageFactory.END_INPUT + 1`.
     *
     * This operation is performed in a coroutine context, allowing for asynchronous execution.
     */
    suspend fun sendEndInput() {
        output.writeByteArray(MessageFactory.messageEndAuth)
        readPacket(MessageFactory.END_INPUT + 1)
    }

    /**
     * Sends the 040A message to the connected device.
     *
     * This method writes the predefined message `MessageFactory.message040A` to the output stream
     * and then reads a packet of data in response. The response corresponds to the packet type
     * `MessageFactory.M_040A + 1`.
     *
     * The method is part of a sequence of messaging operations*/
    suspend fun send040a() {
        output.writeByteArray(MessageFactory.message040A)
        readPacket(MessageFactory.M_040A + 1)
    }

    /**
     * Sends the size of a program to the device.
     *
     * @param size The size of the program, provided as a Long value. This value is encoded
     *             into a message and sent to the device using the associated output stream.
     */
    suspend fun sendSize(size: Long) {
        output.writeByteArray(MessageFactory.messageProgramSize(size))
        readPacket(MessageFactory.PROGRAM_SIZE + 1)
    }

    /**
     * Sends a request to retrieve information about files and processes the response to extract file GUIDs.
     *
     * The method sends a GET request to fetch information about files stored on a remote device and reads
     * the response using the `readPacket` function. The GUIDs of the files are extracted from the response
     * and returned as a list of strings.
     *
     * @return A list of file GUIDs as strings. If no files are present, an empty*/
    suspend fun sendGetFilesInfo() : List<String> {
        output.writeByteArray(MessageFactory.messageGetFilesInfo)
        val buffer = readPacket(MessageFactory.GET_MD5_OF_FILES + 1)
        val size = (buffer[0].toUByte().toUInt() or (buffer[1].toUByte().toUInt() shl 8)).toInt()
        val guids = mutableListOf<String>()
        val sb = StringBuilder()
        if (size == 5) {
            return guids
        }
        for (i in 4 until size) {
            if (buffer[i] == 0.toByte()) {
                guids.add(sb.toString())
                sb.clear()
            } else {
                sb.append(buffer[i].toInt().toChar())
            }
        }
        return guids
    }

    /**
     * Sends a request to retrieve a temporary MD5 hash from the connected device.
     *
     * This method writes a predefined message to the device using the `output` stream
     * and then waits for a response packet that matches the expected type identifier.
     *
     * The operation involves:
     * - Writing the `MessageFactory.messageGetTempMD5` byte array to the output stream.
     * - Reading and validating a response packet using the type identifier `MessageFactory.GET_TEMP_MD5 + 1`.
     **/
    suspend fun sendGetTempMD5() {
        output.writeByteArray(MessageFactory.messageGetTempMD5)
        readPacket(MessageFactory.GET_TEMP_MD5 + 1)
    }

    /**
     * Sends a request to delete files located at the specified positions.
     *
     * This method constructs a message containing the positions of files to delete, sends it
     * over the network, and waits for a response. The positions correspond to the indices of
     * the files on a remote device.
     *
     * @param positions The positions of files to delete. Each position is represented as an integer.
     */
    suspend fun sendDeleteFiles(vararg positions: Int) {
        output.writeByteArray(MessageFactory.messageDeleteFiles(*positions))
        readPacket(MessageFactory.SEND_FILE_COUNT + 1)
    }

    /**
     * Sends a file to the remote device in discrete packets.
     *
     * This method constructs and sends network messages to transfer a file, chunk by chunk,
     * to a remote device. It manages packet buffering and ensures the correct protocol
     * sequence is executed during the file transfer process.
     *
     * @param name The name of the file to be sent. This is included in the initial message
     *             to identify the file being transferred.
     * @param*/
    @OptIn(InternalAPI::class, InternalIoApi::class)
    suspend inline fun sendFile(name: String, reader: (buffer: ByteArray, offset: Int, length: Int) -> Int ) {
        output.writeByteArray(MessageFactory.messageGetFileName(name))
        val buffer = ByteArray(MAX_PACKET_SIZE + 4)
        readPacket(MessageFactory.HEAD_FILE + 1)

        var read = reader(buffer, 4, MAX_PACKET_SIZE)
        buffer[0] = 0x00
        buffer[1] = 0x24
        buffer[2] = 0x19
        buffer[3] = 0x00


        while (read == MAX_PACKET_SIZE) {
            output.writeFully(buffer,0, read + 4)
            readPacket(MessageFactory.PART_OF_FILE + 1)

            read = reader(buffer, 4, MAX_PACKET_SIZE)
            buffer[0] = 0x00
            buffer[1] = 0x24
            buffer[2] = 0x19
            buffer[3] = 0x00
        }
        if (read > 0) {
            buffer[0] = ((read + 4) and 0xff).toByte()
            buffer[1] = ((read + 4) shr 8 and 0xff).toByte()
            output.writeFully(buffer,0, read + 4)
            readPacket(MessageFactory.PART_OF_FILE + 1)
        }
        output.writeByteArray(MessageFactory.messageEndOfFile)
        readPacket(MessageFactory.END_FILE + 1)
    }

    /**
     * Sends the final message to indicate the end of a session or data transfer process.
     *
     * This method writes a predefined end-of-message byte array to the output stream and waits
     * for an acknowledgment or response by reading a packet of a specific type.
     *
     * Behavior:
     * - Writes a byte array representing the end message using the output stream.
     * - Reads a packet with the type identifier `MessageFactory.END_SEND + 1` for further processing or confirmation.
     *
     * Exceptions:
     * Throws*/
    suspend fun sendEnd() {
        output.writeByteArray(MessageFactory.messageEnd)
        readPacket(MessageFactory.END_SEND + 1)
    }

    /**
     * Sends a close message to the connected device to terminate the session.
     *
     * This method writes a predefined close message to the output stream
     * and subsequently waits for an acknowledgment of the close message
     * from the device. The acknowledgment is read as a packet using a
     * specific message type identifier (`MessageFactory.CLOSE + 1`).
     *
     * @throws RuntimeException if the close acknowledgment is not received
     *         after*/
    suspend fun sendClose() {
        output.writeByteArray(MessageFactory.messageClose)
        readPacket(MessageFactory.CLOSE + 1)
    }

    /**
     * Sends media content to a remote device.
     *
     * This method processes the provided media object by reading its source
     * stream and sends it in chunks to the destination specified by the system.
     * The `sendFile` method is internally used to handle the transfer of the
     * data in a controlled manner, ensuring adherence to the maximum allowed
     * packet size for the transfer protocol.
     *
     * @param media The media object to be sent. It represents the source of the content
     **/
    suspend fun sendMedia(media: Media, name: String) {
        media.source().use {
            val buffered = it.buffered()
            var available: Int
            var read: Int
            sendFile(name) { b, o, l ->
                available = 0
                read = buffered.readAvailable(b,o,l)
                while (available < MAX_PACKET_SIZE && read > 0) {
                    available += read
                    read = buffered.readAvailable(b,o + available, min(MAX_PACKET_SIZE - available, l - available))
                }
                available
            }
        }
    }

    /**
     * Uploads the specified main media file along with any additional resources to the server,
     * optionally removing old files from the server before the upload.
     *
     * The method calculates the total size of all files to be uploaded, verifies the current state
     * of files on the server, determines which files need to be uploaded or deleted, and performs
     * the necessary operations to synchronize the media files between the client and the server.
     *
     * @param main The primary `HDMedia` to be uploaded*/
    suspend fun upload(
        main: HDMedia,
        vararg resources: HDMedia,
        deleteOldFiles: Boolean = true
    ) {
        var size: Long = main.size
        for (file in resources) {
            size += file.size
        }
        sendSize(size)
        val m = resources.associateBy { it.md5 }
        val uploadedFiles = mutableListOf<String>()
        var currentFiles = sendGetFilesInfo()
        while (currentFiles.isNotEmpty()) {
            uploadedFiles.addAll(currentFiles)
            currentFiles = sendGetFilesInfo()
        }

        val needToDelete = if (deleteOldFiles) uploadedFiles.indices.toList().toIntArray() else
            uploadedFiles.mapIndexed { i, n -> i to n }.filter { !m.contains(it.second) }.map { it.first }.toIntArray()
        val needToUpload =
            if (deleteOldFiles) resources.toList() else resources.filter { !uploadedFiles.contains(it.md5) }
        sendGetTempMD5()
        sendDeleteFiles(*needToDelete)
        needToUpload.forEach {
            sendMedia(it, it.fileName)
        }
        sendMedia(main, main.fileName)
    }

    /**
     * Closes the current device session by releasing the associated socket resources.
     *
     * This method ensures that the underlying socket used for communication with the device
     * is properly closed. It should be called when the session is no longer needed to
     * prevent resource leaks and ensure graceful shutdown of the connection.
     */
    override fun close() {
        socket.close()
    }
}