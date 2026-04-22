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

package io.github.kshulzh.led4k.common.model

import kotlin.jvm.JvmInline
/**
 * Represents a version encapsulated as an integer value.
 * This class implements the `Version` interface and provides
 * comparison functionality for versions based on their integer value.
 *
 * @property int The integer value representing the version.
 */
@JvmInline
value class IntVersion(val int: Int) : Version {
    /**
     * Compares this instance of `IntVersion` with another `Version` instance.
     *
     * @param other the other `Version` instance to compare to.
     * @return a negative integer, zero, or a positive integer as this instance
     * is less than, equal to, or greater than the specified `Version` instance.
     * @throws UnsupportedOperationException if the provided `Version` instance
     * is not of type `IntVersion`.
     */
    override fun compareTo(other: Version): Int {
        return if (other is IntVersion) {
            int.compareTo(other.int)
        } else {
            version.compareTo(other.version)
        }
    }

    override val version: Int
        get() = int

    /**
     * Secondary constructor that creates an instance of [IntVersion] by parsing
     * the specified string into an integer and delegating to the primary constructor.
     *
     * @param string The string representation of the integer value.
     * @throws NumberFormatException If the string cannot be parsed into an integer.
     */
    constructor(string: String) : this(string.toInt())
}