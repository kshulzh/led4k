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

import io.ktor.utils.io.core.toByteArray

/**
 * Factory object for constructing message-specific byte arrays and defining constants
 * related to message types and identifiers.
 */
object MessageFactory {
    /**
     * Represents a constant used to specify the header file identifier
     * in the data upload operation or message structure for the HDController.
     *
     * This constant plays a role in the protocol definition for communication
     * between the client application and the hardware device.
     */
    const val HEAD_FILE = 0X0017
    /**
     * Represents a constant used as an identifier or flag for specific sections of a file.
     * Its usage can vary depending on the context within file operations or data processing.
     */
    const val PART_OF_FILE = 0x0019
    /**
     * Constant representing the "End of File" marker used in specific communication
     * or file handling processes. This marker is typically utilized to signify the
     * conclusion of a data transmission or file.
     */
    const val END_FILE = 0X001B
    /**
     * Represents a command identifier used in communication with the HD controller
     * to retrieve the temporary MD5 value. This identifier is used to specify the
     * operation type during data exchange.
     */
    const val GET_TEMP_MD5 = 0X0013
    /**
     * A constant used to represent the MD5 checksum calculation operation for a set of files.
     * Typically used to verify file integrity or compare file contents.
     */
    const val GET_MD5_OF_FILES = 0X0011
    /**
     * Constant representing the user GUID time in the system.
     *
     * This value is typically used in contexts where GUID-related timestamps
     * are required. It facilitates identification or processing tasks
     * associated with user-related operations.
     */
    const val USER_GUID_TIME = 0X0410
    /**
     * Represents the identifier for the first message in the communication sequence
     * with the HD device. This constant is used during the initialization of a
     * session or protocol sequence.
     */
    const val FIRST_MESSAGE = 0X000B
    /**
     * A constant representing the command identifier `040A` used for specific communication
     * or operation within the `HDController` context. Its purpose is to signify a particular
     * action or message type during interactions with the device or network protocols.
     */
    const val M_040A = 0X040A
    /**
     * Represents the maximum size for a specific program configuration.
     * This constant defines a limitation used internally by the system
     * to restrict the program's data handling or allocation scope.
     *
     * Value is expressed as a hexadecimal constant.
     */
    const val PROGRAM_SIZE = 0X000F
    /**
     * Represents the hexadecimal value (0x000D) used as a control signal to indicate the end of input.
     * This constant is commonly utilized in communication with hardware controllers or systems
     * to signify the termination of data input or transmission in a session.
     */
    const val END_INPUT = 0x000D
    /**
     * Represents a constant identifier for the file count transmission functionality.
     *
     * The `SEND_FILE_COUNT` variable is used as a unique identifier in processes
     * related to sending or managing the count of files in the system. The identifier
     * is expressed using a hexadecimal value.
     */
    const val SEND_FILE_COUNT = 0X0015
    /**
     * Represents the command code used to signal the end of a data-sending operation.
     *
     * This constant is utilized within network communication or device control logic
     * to indicate the termination of a process involving data transmission.
     */
    const val END_SEND = 0X001D
    /**
     * Represents a constant used to signify the "close" operation in the context of device communication or protocol commands.
     * The specific meaning or usage of this constant is determined by the implementation details of the HDController class
     * or related components.
     */
    const val CLOSE = 0X001F
    /**
     * Represents a constant hexadecimal value `0x0730`.
     *
     * This constant is utilized in the `HDController` as part of the communication
     * protocol with LED hardware devices. Specifically, it appears in methods that
     * handle session management or device-specific commands. Its purpose is to signal
     * or trigger certain operations required by the hardware or protocol during execution.
     */
    const val M_0730 = 0X0730

    /**
     * A constant `ByteArray` representing a specific message payload used for obtaining
     * the temporary MD5 checksum in communication with the HDController.
     */
    val messageGetTempMD5 = byteArrayOf(0x04, 0x00, 0x13, 0x00)

    /**
     * Represents a predefined constant byte array used in communication with
     * the HD device or associated operations.
     *
     * The value of this array consists of specific fixed bytes that may serve
     * as an identifier, header, or command sequence required for communication protocols
     * or device interaction.
     */
    val message0730 = byteArrayOf(0x0c, 0x00, 0x30, 0x07, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)

    /**
     * Constructs a byte array message to delete files at specified positions.
     *
     * The message is structured with a header containing the message size and type,
     * followed by the positions of files to be deleted encoded as 4-byte integers.
     *
     * @param positions The positions of the files to be deleted. Each position is represented as an integer.
     * @return A byte array representing the constructed delete files message.
     */
    fun messageDeleteFiles(vararg positions: Int): ByteArray {
        //todo case when too many files
        val message = ByteArray(positions.size * 4 + 4)
        message[0] = (message.size and 0xff).toByte()
        message[1] = (message.size shr 8 and 0xff).toByte()
        message[2] = 0x15
        message[3] = 0x00
        var i = 4
        positions.forEach {
            message[i++] = (it and 0xff).toByte()
            message[i++] = (it shr 8 and 0xff).toByte()
            message[i++] = (it shr 16 and 0xff).toByte()
            message[i++] = (it shr 24 and 0xff).toByte()
        }
        return message
    }

    /**
     * Represents a byte array command used to request file information from a device.
     *
     * This variable contains a predefined sequence of bytes that corresponds to the
     * "Get Files Info" command used in communication with the hardware device. It is
     * typically sent as part of a protocol used for device interaction, specifically
     * to retrieve metadata or information about files stored on the device.
     */
    val messageGetFilesInfo = byteArrayOf(0x04, 0x00, 0x11, 0x00)

    /**
     * Constructs a byte array message containing a file name.
     *
     * The message is formatted with the following structure:
     * - The first two bytes contain the size of the message in little-endian format.
     * - The third byte is a fixed value (23 in decimal).
     * - The fourth byte is a fixed value (0 in decimal).
     * - The file name is encoded as a byte array and inserted starting at the fifth byte.
     * - The last byte is a null-terminator (0x00).
     *
     * @param name The file name to be included in the message. Must be a non-empty string.
     * @return A byte array representing the constructed message.
     */
    fun messageGetFileName(name: String): ByteArray {
        val message = ByteArray(name.length + 5)
        message[0] = (message.size and 0xff).toByte()
        message[1] = (message.size shr 8 and 0xff).toByte()
        message[2] = 0x17
        message[3] = 0x00
        name.toByteArray().copyInto(message, 4)
        message[message.size - 1] = 0x00

        return message
    }

    /**
     * A predefined byte array representing the "End of File" message or marker
     * used in communication protocols or data streams handled by the HDController.
     *
     * This marker is typically utilized to signal the conclusion of data transmission
     * or processing within the session.
     */
    val messageEndOfFile = byteArrayOf(0x04, 0x00, 0x1b, 0x00)

    /**
     * Generates a 12-byte message that encodes the program size.
     *
     * @param size The size of the program to encode, provided as a Long value.
     * @return A ByteArray of length 12 containing the encoded program size.
     */
    fun messageProgramSize(size: Long): ByteArray {
        var currentSize = size
        val bytes = ByteArray(12)
        bytes[0] = 0x0c
        bytes[1] = 0x00
        bytes[2] = 0x0f
        bytes[3] = 0x00
        var i = 4
        while (currentSize > 0) {
            bytes[i] = (currentSize and 0xffL).toByte()
            currentSize = currentSize shr 8
            i++
        }
        return bytes
    }

    /**
     * Represents a predefined byte array used within the system, potentially for crafting or interpreting
     * protocol-specific messages or commands for communication with an HD device.
     *
     * The values in the array may correspond to specific byte codes or headers required by
     * the communication protocol.
     */
    val messageHello = byteArrayOf(0x08, 0x00, 0x0b, 0x00, 0x09, 0x00, 0x00, 0x01)

    /**
     * Defines a fixed byte array used to indicate the end of an authentication process
     * in communication protocols related to the HD Controller.
     *
     * This constant is part of the messaging structure and typically serves as a
     * marker to signal the conclusion of an authentication sequence.
     */
    val messageEndAuth = byteArrayOf(0x04, 0x00, 0x0d, 0x00)

    /**
     * A static ByteArray that represents the specific sequence of bytes used as a marker
     * or delimiter at the end of a message transmission. This value is typically used in
     * communication protocols to signify the termination of a message.
     */
    val messageEnd = byteArrayOf(0x04, 0x00, 0x1d, 0x00)

    /**
     * Represents a predefined byte array used as a signal or identifier
     * for closing a session or connection with a device.
     *
     * The specific purpose and usage depend on the implementation
     * of the communication protocol with the target device.
     */
    val messageClose = byteArrayOf(0x04, 0x00, 0x1f, 0x00)

    /**
     * Represents a predefined byte array [message040A] used in communication with the HD device.
     *
     * This byte sequence is commonly utilized as part of specific command workflows,
     * particularly in signaling or handshaking operations with the hardware device.
     */
    val message040A = byteArrayOf(0x04, 0x00, 0x0a, 0x04)

    /**
     * Constructs a byte array message containing user information derived from the provided name.
     *
     * The resulting message has the following format:
     * - Byte 0 and 1: Size of the message in little-endian format.
     * - Byte 2: Fixed value 0x10.
     * - Byte 3: Fixed value 0x04.
     * - Byte 4 to (n-2): Bytes of the provided name.
     * - Last Byte: Fixed terminator value 0x00.
     *
     * @param name The name of the user to be included in the message. Cannot be null or empty.
     * @return A ByteArray representing the constructed user information message.
     */
    fun messageUserInfo(name: String): ByteArray {
        val message = ByteArray(name.length + 5)
        message[0] = (message.size and 0xff).toByte()
        message[1] = (message.size shr 8 and 0xff).toByte()
        message[2] = 0x10
        message[3] = 0x04
        name.toByteArray().copyInto(message, 4)
        message[message.size - 1] = 0x00

        return message
    }

    /**
     * Represents a byte array that defines the "Get Model" message for device communication.
     * This message is typically used to query the model information from a hardware device.
     * The byte sequence is predefined and interpreted by the corresponding device firmware.
     */
    val messageGetModel = byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x01, 0x00)
}