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

import io.github.kshulzh.led4k.common.effect.BasicEffects
import io.github.kshulzh.led4k.common.effect.Effect
import io.github.kshulzh.led4k.common.media.Media
import io.github.kshulzh.led4k.common.utils.Led4kAttributes

/**
 * Implementation of the `ImageArea` interface extending functionality from `BaseArea` and `Led4kAttributes`.
 *
 * This class represents a spatially bounded region configured to display image-based media. It provides
 * customization of dimensions, location, and effects for rendering within a coordinate system. Additionally,
 * it supports LED-specific attributes for specialized hardware configurations.
 *
 * @param x Specifies the horizontal position of the area's top-left corner in pixels. Defaults to 0.
 * @param y Specifies the vertical position of the area's top-left corner in pixels. Defaults to 0.
 * @param width Defines the horizontal size of the area in pixels. Defaults to 128.
 * @param height Defines the vertical size of the area in pixels. Defaults to 128.
 * @param effect Assigns an `Effect` type to augment rendering behavior. Defaults to `BasicEffects.DEFAULT`.
 * @param led4kAttributes Provides a mutable map for storing LED-specific configuration attributes. Defaults
 *        to an empty map.
 */
class ImageAreaImpl(
    override var x: Int = 0,
    override var y: Int = 0,
    override var width: Int = 128,
    override var height: Int = 128,
    var effect: Effect = BasicEffects.DEFAULT,
    override val led4kAttributes: MutableMap<String, Any?> = mutableMapOf(),
) : ImageArea, BaseArea, Led4kAttributes {
    /**
     * Specifies the media content to be displayed within the area.
     *
     * This property is a core component of a media-oriented area implementation,
     * such as an image or video area. It defines the media asset that will be
     * rendered or processed within the spatial boundaries of the area.
     *
     * The `media` property must be set to a valid implementation of the `Media`
     * interface before the containing area can be utilized effectively. Any changes
     * to this property during runtime may dynamically update the media being displayed.
     */
    override lateinit var media: Media
}