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

import kotlinx.io.RawSource
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

/**
 * Represents a media source based on a file path.
 *
 * This class implements the `Media` interface and provides functionality
 * to work with media content stored at a specific file system path. It leverages
 * a `Path` for accessing and managing the media file, making it suitable for
 * scenarios where media is persisted in the file system.
 *
 * @property path The file system path representing the location of the media.
 * @property led4kAttributes A mutable map of attributes associated with the media, allowing
 * customization and extensibility.
 */
class PathMedia(
    val path: Path,
    override val led4kAttributes: MutableMap<String, Any?> = mutableMapOf(),
) : Media {
    /**
     * Constructs a `PathMedia` instance using a string representation of a file path.
     *
     * This constructor allows the creation of a `PathMedia` object by providing
     * a file path as a string, which is then internally converted into a `Path` object.
     * It serves as a convenient way to initialize media objects when dealing with
     * file paths in string format.
     *
     * @param path The string representation of the file path to be used for the media.
     */
    constructor(path: String) : this(Path(path))

    /**
     * Provides the source of raw data for the media.
     *
     * This method returns a `RawSource` instance, allowing access to the
     * underlying data associated with the media object. The implementation
     * utilizes the system file system to obtain the source from the specified path.
     *
     * @return a `RawSource` representing the raw data of the media.
     */
    override fun source(): RawSource {
        return SystemFileSystem.source(path)
    }
}