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

/**
 * Defines the base structure for an area, encompassing position and dimensions.
 * Classes implementing this interface represent spatially bounded components within
 * a system, characterized by x and y coordinates and width and height dimensions.
 *
 * This interface extends the more general `Area` interface, adding specific
 * properties to represent spatial attributes.
 */
interface BaseArea : Area {
    /**
     * Represents the x-coordinate of the top-left corner of an area.
     *
     * This property defines the horizontal position of the area within its
     * parent container or coordinate system. The value is expressed in pixels
     * relative to the coordinate system's origin (0, 0).
     *
     * Implementing classes and instances may use this property to determine or
     * adjust the area's placement within a broader layout or visual representation.
     */
    var x: Int
    /**
     * Represents the y-coordinate of the area.
     *
     * This property defines the vertical position of an area within a coordinate system
     * or layout. It can be used to specify or adjust the placement of this area relative
     * to other elements or the overall context in which it is rendered or structured.
     */
    var y: Int
    /**
     * Defines the width of the area.
     *
     * This property specifies the horizontal size of the area in terms of pixels and
     * is utilized for layout calculations, rendering, or bounding spatial elements.
     * Its value can be adjusted dynamically to resize the area as needed.
     */
    var width: Int
    /**
     * Defines the height of the area in pixels.
     *
     * This property specifies the vertical dimension of the area, and its value can
     * be adjusted dynamically to alter the size or layout of the area. The height
     * is typically used in conjunction with other properties like `width`, `x`, and
     * `y` to define the complete spatial boundaries of an area within a system.
     */
    var height: Int
}