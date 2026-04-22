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

package io.github.kshulzh.led4k.common.builder

import io.github.kshulzh.led4k.common.area.AreaContainer
import io.github.kshulzh.led4k.common.area.AreaContainerImpl
import io.github.kshulzh.led4k.common.area.BaseArea
import io.github.kshulzh.led4k.common.area.ImageArea
import io.github.kshulzh.led4k.common.area.ImageAreaImpl
import io.github.kshulzh.led4k.common.area.VideoArea
import io.github.kshulzh.led4k.common.area.VideoAreaImpl
import io.github.kshulzh.led4k.common.program.ListProgram
import io.github.kshulzh.led4k.common.program.ListProgramImpl
import io.github.kshulzh.led4k.common.program.SingleProgram
import io.github.kshulzh.led4k.common.program.SingleProgramImpl

/**
 * Creates and initializes a new instance of a `SingleProgram` using the given initialization block.
 *
 * This function facilitates the creation of a `SingleProgram` by applying the provided initialization
 * block to a newly created `SingleProgramImpl` instance. The initialization block allows for the
 * configuration of areas, play modes, schedules, and other attributes defined in the `SingleProgram`.
 *
 * @param init A lambda block that takes a `SingleProgram` receiver, enabling configuration of the program.
 * @return A fully initialized instance of `SingleProgram` configured by the given initialization block.
 */
fun program(init: SingleProgram.() -> Unit): SingleProgram {
    return SingleProgramImpl().apply(init)
}

/**
 * Creates and initializes a new instance of `ListProgram` using the provided initialization lambda.
 *
 * @param init A lambda function to configure the newly created `ListProgram` instance.
 * @return A new instance of `ListProgram` initialized with the specified configuration.
 */
fun listProgram(init: ListProgram.() -> Unit): ListProgram {
    return ListProgramImpl().apply(init)
}

/**
 * Creates and adds an `ImageArea` to the list of areas in the `AreaContainer`.
 *
 * This method initializes a new instance of `ImageAreaImpl`, applies the configuration
 * provided via the `init` lambda, and registers the newly created area within the container.
 * The resulting `ImageArea` can be used to represent and manage image-related media within the system.
 *
 * @param init A lambda function used to configure the `ImageAreaImpl` instance.
 *             This function allows customization of the image area's properties, such as
 *             dimensions, position, and other attributes.
 * @return The newly created `ImageArea` instance.
 */
fun AreaContainer.image(init: ImageAreaImpl.() -> Unit): ImageArea {
    return ImageAreaImpl().apply(init).also {
        areas.add(it)
    }
}

/**
 * Adds a new `AreaContainerImpl` to the list of areas within the current `AreaContainer`
 * and initializes it using the provided configuration block.
 *
 * This method allows the creation and customization of a nested `AreaContainerImpl`
 * instance, which can then be used to define hierarchical or composite structures of areas.
 *
 * @param init A lambda function used to configure the properties of the newly created
 * `AreaContainerImpl` instance. This function operates on the new instance and allows
 * setting up its properties such as size, position, or custom configurations.
 *
 * @return The newly created and initialized `AreaContainerImpl` instance, which is also
 * added to the `areas` list in the current `AreaContainer`.
 */
fun AreaContainer.container(init: AreaContainerImpl.() -> Unit): AreaContainerImpl {
    return AreaContainerImpl().apply(init).also {
        areas.add(it)
    }
}

/**
 * Adds a video area to the container, initializing its properties using the provided configuration block.
 * The newly created `VideoArea` is appended to the `areas` list of the `AreaContainer`.
 *
 * @param init A configuration block used to initialize the properties of the created `VideoAreaImpl`.
 *             This lambda receiver provides access to the `VideoAreaImpl` instance, allowing its attributes
 *             (such as size, location, and media) to be configured dynamically.
 * @return The created and configured `VideoArea` instance.
 */
fun AreaContainer.video(init: VideoAreaImpl.() -> Unit): VideoArea {
    return VideoAreaImpl().apply(init).also {
        areas.add(it)
    }
}

/**
 * Sets the size of the area by updating its width and height dimensions.
 *
 * @param width The new width of the area in pixels.
 * @param height The new height of the area in pixels.
 */
fun BaseArea.size(width: Int, height: Int) {
    this.width = width
    this.height = height
}

/**
 * Updates the position of the area by setting its top-left corner coordinates.
 *
 * This function modifies the `x` and `y` properties of the `BaseArea` instance,
 * allowing for repositioning within its parent container or layout.
 *
 * @param x The new x-coordinate of the area's top-left corner.
 * @param y The new y-coordinate of the area's top-left corner.
 */
fun BaseArea.location(x: Int, y: Int) {
    this.x = x
    this.y = y
}