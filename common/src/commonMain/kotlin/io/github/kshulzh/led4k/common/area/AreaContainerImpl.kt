/*
 * Copyright (c) 2025-2026. Kirill Shulzhenko
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

import io.github.kshulzh.led4k.common.utils.Led4kAttributes

/**
 * Implementation of `BaseArea`, `AreaContainer`, and `Led4kAttributes` that represents a container
 * capable of managing multiple areas within a system. This class is designed to provide spatial
 * properties and organizational functionality for hierarchical or composite area configurations.
 *
 * @property x The x-coordinate of the top-left corner of the area.
 * @property y The y-coordinate of the top-left corner of the area.
 * @property width The width of the area.
 * @property height The height of the area.
 * @property areas A mutable list of `Area` objects managed by this container.
 * @property led4kAttributes A map of attributes associated with the LED 4K system configuration.
 */
class AreaContainerImpl(
    override var x: Int = 0,
    override var y: Int = 0,
    override var width: Int = 128,
    override var height: Int = 128,
    override var areas: MutableList<Area> = mutableListOf(),
    override val led4kAttributes: MutableMap<String, Any?> = mutableMapOf(),
) : BaseArea, AreaContainer, Led4kAttributes