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

package io.github.kshulzh.led4k.common.model

/**
 * Represents a version abstraction that can be compared to other versions.
 *
 * The `Version` interface defines a common contract for comparing different implementations
 * of versions. A version can be encapsulated as various types, such as integers, strings,
 * or a series of versions. The comparison behavior depends on the implementation.
 *
 * Implementations of this interface must override `compareTo` to provide a specific
 * comparison logic.
 */
interface Version : Comparable<Version> {
    override fun compareTo(other: Version): Int

    val version: Int
}
