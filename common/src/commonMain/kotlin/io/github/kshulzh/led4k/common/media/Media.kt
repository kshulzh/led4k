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

package io.github.kshulzh.led4k.common.media

import io.github.kshulzh.led4k.common.utils.Led4kAttributes
import kotlinx.io.RawSource

/**
 * Represents a base abstraction for media-related objects.
 *
 * This interface serves as a marker or contract for objects that are
 * categorized as media. Implementations of this interface may include
 * various types of media, such as image files, video files, or other
 * multimedia content. It forms the foundation for defining media-related
 * components in the system.
 */
interface Media : Led4kAttributes {
    /**
     * Companion object for the Media interface.
     * Provides shared functionality or constants related to Media implementations.
     */
    companion object

    fun source(): RawSource
}