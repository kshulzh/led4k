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
 * Represents a collection of version instances, which can be compared collectively
 * as a single version unit. This class implements the `Version` interface and provides
 * functionality to compare `Versions` objects based on their contained versions.
 *
 * @property versions A list of `Version` instances encapsulated within this collection.
 */
@JvmInline
value class Versions(
    val versions: List<Version>
) : Version {
    /**
     * Companion object for the `Versions` class, providing utility functions
     * to create instances of `Versions` from string inputs.
     */
    companion object {

        /**
         * Parses a dot-separated string representation of integers into a `Versions` object.
         * Each segment of the string is interpreted as an integer and converted into an `IntVersion` instance.
         *
         * @param string The dot-separated string containing integer values to be parsed.
         * @return A `Versions` object containing a list of `IntVersion` instances parsed from the string.
         */
        fun ofInts(string: String): Versions {
            return Versions(string.split(".").map { IntVersion(it) })
        }
    }

    /**
     * Compares this instance of `Versions` with another `Version` instance.
     *
     * The comparison is performed by comparing the individual elements within the
     * `versions` list of both instances. Both instances must be of type `Versions`,
     * and their `versions` lists must have the same size. If these conditions are not met,
     * an `UnsupportedOperationException` will be thrown.
     *
     * @param other the other `Version` instance to compare to.
     * @return a negative integer, zero, or a positive integer as this instance
     * is less than, equal to, or greater than the specified `Version` instance.
     * @throws UnsupportedOperationException if the provided `Version` instance is not
     * of type `Versions` or if the sizes of their `versions` lists are not equal.
     */
    override fun compareTo(other: Version): Int {
        return if (other is Versions && other.versions.size == versions.size) {
            val it1 = versions.iterator()
            val it2 = other.versions.iterator()
            var res = 0
            while (it1.hasNext() && res == 0) {
                res = it1.next().compareTo(it2.next())
            }
            res
        } else {
            version.compareTo(other.version)
        }
    }

    override val version: Int
        get() = versions.map { it.version }.reduce { acc, i -> acc * 100 + i }
}