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

package io.github.kshulzh.led4k.hd.fullcolor.media

import io.github.kshulzh.led4k.common.media.Media

/**
 * Represents a high-definition media object, providing additional metadata and extensions.
 *
 * This class encapsulates media content while adding specific attributes such as
 * file checksum (MD5), file extension, and size. It leverages delegation to extend
 * functionality from the base `Media` interface while introducing attributes specific
 * to high-definition media representations.
 *
 * @property media The underlying `Media` object, representing the core media content.
 * @property md5 The MD5 checksum of the media file, used for integrity verification.
 * @property extension The file extension of the media, indicating its format (e.g., `png`, `mp4`).
 * @property size The size of the media file in bytes.
 */
class HDMedia(
    val media: Media,
    val md5: String,
    val extension: String,
    val size: Long,
) : Media by media {
    /**
     * Lazily computes the file name for the media object based on its MD5 hash and file extension.
     *
     * The resulting file name is in the format "<md5>.<extension>", where:
     * - `md5` is a unique identifier for the file's content.
     * - `extension` represents the file's type (e.g., jpg, mp4).
     *
     * This property is primarily used to generate consistent file names for file handling
     * operations, such as uploads or storage.
     */
    val fileName by lazy { "${md5}.$extension" }
}