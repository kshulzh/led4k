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

/**
 * Represents an interface for storing and managing LED 4k attributes.
 *
 * This interface defines a contract for classes that hold a collection of attributes,
 * where each attribute is represented as a key-value pair with the key being a `String`
 * and the value being of any nullable type.
 */
interface Led4kAttributes {
    /**
     * A mutable map that stores key-value pairs representing attributes associated with a `Led4kAttributes` entity.
     *
     * This map provides a dynamic and flexible structure to define and manage various attributes where the keys
     * are strings and the values can be of any type. It allows extensions or modifications specific to the needs
     * of the containing entity.
     */
    val led4kAttributes: MutableMap<String, Any?>
}