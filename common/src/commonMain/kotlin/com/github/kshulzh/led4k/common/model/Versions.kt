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

package com.github.kshulzh.led4k.common.model

@JvmInline
value class Versions(
    val versions: List<Version>
) : Version {
    companion object {
        fun ofStrings(string: String): Versions {
            return Versions(string.split(".").map { StringVersion(it) })
        }

        fun ofInts(string: String): Versions {
            return Versions(string.split(".").map { IntVersion(it) })
        }
    }

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
            throw UnsupportedOperationException()
        }
    }
}