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

import com.github.kshulzh.led4k.hd.fullcolor.media.HDMedia
import com.github.kshulzh.led4k.media.InputStream
import com.github.kshulzh.led4k.media.Paths
import com.github.kshulzh.led4k.media.network.TCPConnection
import com.github.kshulzh.led4k.media.outputStream

class HDDevice(val connection: TCPConnection) {
    companion object {
        const val MAX_PACKET_SIZE = 9212
        val EMPTY_BYTE_ARRAY = ByteArray(0)

        const val HEAD_FILE = 0X0017
        const val PART_OF_FILE = 0x0019
        const val END_FILE = 0X001B
        const val GET_TEMP_MD5 = 0X0013
        const val GET_MD5_OF_FILES = 0X0011
        const val USER_GUID_TIME = 0X0410
        const val FIRST_MESSAGE = 0X000B
        const val M_040A = 0X040A
        const val PROGRAM_SIZE = 0X000F
        const val END_INPUT = 0x000D
        const val SEND_FILE_COUNT = 0X0015
        const val END_SEND = 0X001D
        const val CLOSE = 0X001F
        const val M_0730 = 0X0730
    }

    fun setPacketMeta(bytes: ByteArray, type: Int) {
        bytes[0] = (bytes.size and 0xff).toByte()
        bytes[1] = (bytes.size shr 8 and 0xff).toByte()
        bytes[2] = (type and 0xff).toByte()
        bytes[3] = (type shr 8 and 0xff).toByte()
    }

    fun sendPacket(bytes: ByteArray, type: Int) {
        val size = bytes.size + 4
        val message = ByteArray(size)
        setPacketMeta(message, type)

        bytes.copyInto(message, 4)
        connection.write(message)
    }

    fun readPacket(): ByteArray {
        val rawSize = ByteArray(2)
        connection.read(rawSize)
        val size = rawSize[0].toInt() or (rawSize[1].toInt() shl 8)
        if (size < 0 || size > 65000) {
            throw RuntimeException("Connection closed During execution")
        }
        val bytes = ByteArray(size)
        rawSize.copyInto(bytes)
        connection.read(bytes, 2)
        return bytes
    }

    fun readType(type: Int): ByteArray {
        var bytes: ByteArray = readPacket()
        while (bytes[2] + (bytes[3].toInt() shl 8) != type) {
            bytes = readPacket()
        }
        return bytes
    }

    fun request(bytes: ByteArray, type: Int): ByteArray {
        sendPacket(bytes, type)
        return readType(type + 1)
    }

    fun getTempMD5(): ByteArray {
        return request(EMPTY_BYTE_ARRAY, GET_TEMP_MD5)
    }

    fun send0730(): ByteArray {
        return request(byteArrayOf(2, 0, 0, 0, 0, 0, 0, 0), M_0730)
    }

    fun sendDeleteFiles(vararg positions: Int) {
        val message = ByteArray(positions.size * 4)
        var i = 0
        while (i < message.size) {
            message[i] = (positions[i / 4]).toByte()
            i += 4
        }
        request(message, SEND_FILE_COUNT)
    }

    fun getFilesInfo(): List<String> {
        val result = request(EMPTY_BYTE_ARRAY, GET_MD5_OF_FILES)
        request(EMPTY_BYTE_ARRAY, GET_MD5_OF_FILES)
        val guids = mutableListOf<String>()
        val sb = StringBuilder()
        for (i in 4 until result.size) {
            if (result[i] == 0.toByte()) {
                guids.add(sb.toString())
                sb.clear()
            } else {
                sb.append(result[i].toInt().toChar())
            }
        }
        return guids
    }

    fun upload(name: String, inputStream: InputStream) {
        request((name + '\u0000').toByteArray(), HEAD_FILE)
        var bytes = ByteArray(MAX_PACKET_SIZE + 4)
        var remaining = inputStream.available()
        while (remaining > MAX_PACKET_SIZE) {
            inputStream.read(bytes, 4, MAX_PACKET_SIZE)
            setPacketMeta(bytes, PART_OF_FILE)
            connection.write(bytes)
            readType(PART_OF_FILE + 1)
            remaining -= MAX_PACKET_SIZE
        }
        if (remaining > 0) {
            bytes = ByteArray(remaining + 4)
            inputStream.read(bytes, 4, remaining)
            setPacketMeta(bytes, PART_OF_FILE)
            connection.write(bytes)
            readType(PART_OF_FILE + 1)
        }
        request(EMPTY_BYTE_ARRAY, END_FILE)
    }

    fun upload(hdMedia: HDMedia) {
        upload(hdMedia.fileName, hdMedia.inputStream)
    }

    fun sendSize(size: Long) {
        var currentSize = size
        val bytes = ByteArray(8)
        var i = 0
        while (currentSize > 0) {
            bytes[i] = (currentSize and 0xffL).toByte()
            currentSize = currentSize shr 8
            i++
        }
        request(bytes, PROGRAM_SIZE)
    }

    fun upload(main: HDMedia, vararg resources: HDMedia, deleteOldFiles: Boolean = true) {
        resources.forEach {
            it.inputStream.transferTo(Paths.get(it.fileName).outputStream)
        }
        main.inputStream.transferTo(Paths.get(main.fileName).outputStream)
        var size: Long = main.inputStream.available().toLong()
        for (file in resources) {
            size += file.inputStream.available()
        }
        sendSize(size)
        val m = resources.associateBy { it.md5 }
        val uploadedFiles = getFilesInfo()
        val needToDelete = if (deleteOldFiles) uploadedFiles.indices.toList().toIntArray() else
            uploadedFiles.mapIndexed { i, n -> i to n }.filter { !m.contains(it.second) }.map { it.first }.toIntArray()
        val needToUpload =
            if (deleteOldFiles) resources.toList() else resources.filter { !uploadedFiles.contains(it.md5) }
        getTempMD5()
        sendDeleteFiles(*needToDelete)
        needToUpload.forEach(this::upload)
        upload(main)
    }

    fun sendFirstMessage() {
        connection.open()
        request(byteArrayOf(0x09, 0x00, 0x00, 0x01), FIRST_MESSAGE)
    }

    fun sendInfo(info: String) {
        request(info.toByteArray(), USER_GUID_TIME)
        request(EMPTY_BYTE_ARRAY, END_INPUT)
    }

    fun sendOldInfo(info: String) {
        request(EMPTY_BYTE_ARRAY, END_INPUT)
    }

    fun sendEnd() {
        request(EMPTY_BYTE_ARRAY, END_SEND)
    }

    fun close() {
        request(EMPTY_BYTE_ARRAY, CLOSE)
        connection.close()
    }

    fun send040A() {
        request(EMPTY_BYTE_ARRAY, M_040A)
    }
}