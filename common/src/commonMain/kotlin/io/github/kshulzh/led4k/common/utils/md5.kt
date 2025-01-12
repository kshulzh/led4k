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

package io.github.kshulzh.led4k.common.utils

//todo optimize
fun ByteArray.MD5(): ByteArray {
    val message = this
    val bitLength = message.size.toLong() * 8

    // Padding
    val padded = message.toMutableList()
    padded.add(0x80.toByte())
    while ((padded.size % 64) != 56) {
        padded.add(0x00)
    }

    // Append original length in bits, little-endian 64-bit
    for (i in 0 until 8) {
        padded.add(((bitLength ushr (8 * i)) and 0xff).toByte())
    }

    var a0 = 0x67452301
    var b0 = 0xefcdab89.toInt()
    var c0 = 0x98badcfe.toInt()
    var d0 = 0x10325476

    val s = intArrayOf(
        7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
        5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
        4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
        6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21
    )

    val k = IntArray(64) { i ->
        ((kotlin.math.abs(kotlin.math.sin((i + 1).toDouble())) * (1L shl 32)).toLong() and 0xffffffffL).toInt()
    }

    for (offset in padded.indices step 64) {
        val m = IntArray(16)
        for (i in 0 until 16) {
            val j = offset + i * 4
            m[i] = (padded[j].toInt() and 0xff) or
                    ((padded[j + 1].toInt() and 0xff) shl 8) or
                    ((padded[j + 2].toInt() and 0xff) shl 16) or
                    ((padded[j + 3].toInt() and 0xff) shl 24)
        }

        var a = a0
        var b = b0
        var c = c0
        var d = d0

        for (i in 0 until 64) {
            val (f, g) = when (i) {
                in 0..15 -> ((b and c) or (b.inv() and d)) to i
                in 16..31 -> ((d and b) or (d.inv() and c)) to ((5 * i + 1) % 16)
                in 32..47 -> (b xor c xor d) to ((3 * i + 5) % 16)
                else -> (c xor (b or d.inv())) to ((7 * i) % 16)
            }

            val temp = d
            d = c
            c = b
            b += (a + f + k[i] + m[g]).rotateLeft(s[i])
            a = temp
        }

        a0 += a
        b0 += b
        c0 += c
        d0 += d
    }

    return byteArrayOf(
        (a0 and 0xff).toByte(), ((a0 ushr 8) and 0xff).toByte(), ((a0 ushr 16) and 0xff).toByte(), ((a0 ushr 24) and 0xff).toByte(),
        (b0 and 0xff).toByte(), ((b0 ushr 8) and 0xff).toByte(), ((b0 ushr 16) and 0xff).toByte(), ((b0 ushr 24) and 0xff).toByte(),
        (c0 and 0xff).toByte(), ((c0 ushr 8) and 0xff).toByte(), ((c0 ushr 16) and 0xff).toByte(), ((c0 ushr 24) and 0xff).toByte(),
        (d0 and 0xff).toByte(), ((d0 ushr 8) and 0xff).toByte(), ((d0 ushr 16) and 0xff).toByte(), ((d0 ushr 24) and 0xff).toByte()
    )
}