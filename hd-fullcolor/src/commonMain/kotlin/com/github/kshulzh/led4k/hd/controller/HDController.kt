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

import com.github.kshulzh.led4k.common.controller.RemoteController
import com.github.kshulzh.led4k.common.io.File
import com.github.kshulzh.led4k.common.io.extractFileName
import com.github.kshulzh.led4k.common.model.areas.Area

expect fun HDController.of(ip: String): HDController
abstract class HDController(override var ip: String) : RemoteController {
    companion object {
        val EMPTY_BYTE_ARRAY = ByteArray(0)
        val MAX_PACKET_SIZE = 9212

        val HEAD_FILE = 0X0017
        val PART_OF_FILE = 0x0019
        val END_FILE = 0X001B
        val GET_TEMP_MD5 = 0X0013
        val GET_MD5_OF_FILES = 0X0011
        val USER_GUID_TIME = 0X0410
        val FIRST_MESSAGE = 0X000B
        val M_040A = 0X040A
        val PROGRAM_SIZE = 0X000F
        val END_INPUT = 0x000D
        val SEND_FILE_COUNT = 0X0015
        val END_SEND = 0X001D
        val CLOSE = 0X001F
    }

    lateinit var appVersion: String
    lateinit var model: String
    lateinit var timeZone: String
    lateinit var svnVersion: String
    var port = 9527
    abstract fun write(bytes: ByteArray, type: Int)
    abstract fun read(): ByteArray

    fun readType(type: Int): ByteArray {
        var bytes: ByteArray
        bytes = read()
        while (bytes[2] + (bytes[3].toInt() shl 8) != type.toInt()) {
            bytes = read()
        }
        return bytes
    }

    fun request(bytes: ByteArray, type: Int): ByteArray {
        write(bytes, type)
        return readType(type + 1)
    }

    override fun send(area: Area) {
        TODO("Not yet implemented")
    }

    //todo length can be not equals 8
    fun getFilesInfo(): MutableList<String> {
        val result = request(EMPTY_BYTE_ARRAY, GET_MD5_OF_FILES)
        request(EMPTY_BYTE_ARRAY, GET_MD5_OF_FILES)
        val guids = Array<ByteArray>(result.size / 33) {
            ByteArray(32)
        }
        var temp = 0
        var i = 4
        while (i < result.size) {
            for (j in 0..31) {
                guids[temp][j] = result[i]
                i++
            }
            temp++
            i++
        }
        return guids.map(ByteArray::toString).toMutableList()
    }

    fun getTempMD5(): ByteArray {
        return request(EMPTY_BYTE_ARRAY, GET_TEMP_MD5)
    }

    fun sendDeleteFiles(vararg positions: Int) {
        val message = ByteArray(positions.size * 4)
        var i = 0
        while (i < message.size) {
            message[i] = positions[i / 4].toByte()
            i += 4
        }
        request(message, SEND_FILE_COUNT)
    }

//    fun sendFile(f: File) {
//        request((f.name + '\u0000').toByteArray(), HEAD_FILE)
//        val bytes = cutFile(f)
//        for (aByte in bytes) {
//            request(aByte, PART_OF_FILE)
//        }
//        request(EMPTY_BYTE_ARRAY, END_FILE)
//    }

    fun sendFile_v2(f: File) {
        request((f.name + '\u0000').encodeToByteArray(), HEAD_FILE)
        val size = f.size
        f.open()
        val bytes = ByteArray(MAX_PACKET_SIZE)
        var i: Long = 0
        while (i < size - MAX_PACKET_SIZE) {
            f.read(bytes)
            request(bytes, PART_OF_FILE)
            i += MAX_PACKET_SIZE.toLong()
        }
        if (i < size) {
            val bytes1 = ByteArray((size - i).toInt())
            f.read(bytes1)
            request(bytes1, PART_OF_FILE)
        }
        request(EMPTY_BYTE_ARRAY, END_FILE)
        f.close()
    }

    fun sendFirstMessage() {
        request(byteArrayOf(0x07, 0x00, 0x00, 0x01), FIRST_MESSAGE)
    }

    fun sendInfo(info: String) {
        request(info.encodeToByteArray(), USER_GUID_TIME)
        request(EMPTY_BYTE_ARRAY, END_INPUT)

    }

    fun sendOldInfo(info: String?) {
        request(EMPTY_BYTE_ARRAY, END_INPUT)
    }

    fun sendEnd() {
        request(EMPTY_BYTE_ARRAY, END_SEND)
    }

    fun close() {
        request(EMPTY_BYTE_ARRAY, CLOSE)
    }

    fun send040A() {
        request(EMPTY_BYTE_ARRAY, M_040A)
    }

    fun sendSize(size: Long) {
        var size = size
        val bytes = ByteArray(8)
        var i = 0
        while (size > 0) {
            bytes[i] = (size and 0xffL).toByte()
            size = size shr 8
            i++
        }
        request(bytes, PROGRAM_SIZE)
    }

    fun uploadFiles(main: File, deleteOldFiles: Boolean, vararg files: File) {
        var size: Long = 0
        for (file in files) {
            size += file.size
        }
        size += main.size
        sendSize(size)
        val uploadedFiles = getFilesInfo()
        val newFileNames = files.map(File::name).map(::extractFileName)
        val needToDelete = mutableListOf<Int>()
        val needToUpload = mutableListOf<Boolean>()
        if (!deleteOldFiles) {
            uploadedFiles.forEachIndexed { i, name ->
                {
                    val contains = newFileNames.contains(name)
                    needToUpload.add(!contains)
                    if (contains) {
                        needToDelete.add(i)
                    }
                }
            }
            getTempMD5()
            sendDeleteFiles(*needToDelete.toIntArray())
            needToUpload.forEachIndexed { index, b -> if (b) sendFile_v2(files[index]) }
        } else {
            getTempMD5()
            (0 until uploadedFiles.size).forEach {
                needToDelete.add(it)
            }
            sendDeleteFiles(*needToDelete.toIntArray())
            files.forEach {
                sendFile_v2(it)
            }
        }
        sendFile_v2(main)
    }
}