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
import io.github.kshulzh.led4k.common.utils.Led4kAttributes

/**
 * Implementation of a video area that defines spatial boundaries and attributes
 * for video media management within a system.
 *
 * This class combines the functionalities from the `VideoArea`, `BaseArea`, and
 * `Led4kAttributes` interfaces. It represents a specialized area specifically
 * intended for handling video content, while also supporting custom attributes
 * and spatial configurations.
 *
 * @property x The horizontal position of the area within the coordinate system.
 * @property y The vertical position of the area within the coordinate system.
 * @property width The width of the area in pixels, defining the horizontal size.
 * @property height The height of the area in pixels, defining the vertical size.
 * @property led4kAttributes A collection of customizable attributes specific to
 *                           4K LED-related configurations.
 */
class VideoAreaImpl(
    override var x: Int = 0,
    override var y: Int = 0,
    override var width: Int = 128,
    override var height: Int = 128,
    override val led4kAttributes: MutableMap<String, Any?> = mutableMapOf(),
) : VideoArea, BaseArea, Led4kAttributes {
    /**
     * Represents the media content associated with this video area.
     *
     * This property allows specifying and managing the media resource (e.g., video, image, or other
     * media types) that is rendered or manipulated within the spatial boundaries of this `VideoAreaImpl`.
     * The property is initialized at runtime, enabling dynamic association of media objects.
     */
    override lateinit var media: Media
}