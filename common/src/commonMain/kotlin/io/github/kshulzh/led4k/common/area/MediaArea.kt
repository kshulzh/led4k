/*
 * Copyright (c) 2024-2026. Kirill Shulzhenko
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

package io.github.kshulzh.led4k.common.area

import io.github.kshulzh.led4k.common.media.Media

/**
 * Represents a specialized type of area dedicated to managing and displaying media content.
 *
 * This interface extends the general `Area` interface, incorporating behavior and properties
 * specific to media handling. It provides a mechanism to associate a media object with a spatial
 * boundary, enabling rendering, layout, or other media-specific operations within a defined region.
 */
interface MediaArea : Area {
    /**
     * Represents the media associated with this area.
     *
     * This property defines the type of media content (such as images, videos, or other media)
     * that is managed or displayed within the area. Implementing classes are expected to provide
     * specific functionality for handling the associated media, including rendering, playback,
     * resizing, or other media-related operations.
     */
    var media: Media
}