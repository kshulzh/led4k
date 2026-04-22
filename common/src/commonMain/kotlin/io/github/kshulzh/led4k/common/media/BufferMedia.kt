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

package io.github.kshulzh.led4k.common.media

import kotlinx.io.Buffer
import kotlinx.io.RawSource

/**
 * Represents a media source backed by an in-memory buffer.
 *
 * This class implements the `Media` interface and provides
 * functionality to read media content directly from a `Buffer`
 * instance. It provides a way to interact with media data that
 * is stored entirely in memory, making it suitable for scenarios
 * where media content is dynamically generated or doesn't exist as a file.
 *
 * @property buffer The in-memory buffer containing the media data.
 * @property led4kAttributes A mutable map of attributes associated with the media, allowing
 * customization and extensibility.
 */
class BufferMedia(
    val buffer: Buffer,
    override val led4kAttributes: MutableMap<String, Any?> = mutableMapOf(),
) : Media {
    /**
     * Provides the source of raw data for the media.
     *
     * This method returns a `RawSource` instance, which allows access to the
     * underlying data encapsulated in the media object. The implementation
     * uses the buffer to obtain a peekable view of the data.
     *
     * @return a `RawSource` representing the raw data of the media.
     */
    override fun source(): RawSource {
        return buffer.peek()
    }
}