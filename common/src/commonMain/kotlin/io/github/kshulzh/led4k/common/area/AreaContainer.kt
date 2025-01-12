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
 * Represents a container for managing multiple areas within a system.
 * Provides the ability to group and organize areas, enabling hierarchical
 * structures and composite area configurations.
 */
interface AreaContainer : Area {
    /**
     * Holds a mutable list of areas that can be used to group or organize individual `Area` elements.
     *
     * This property is typically utilized by classes implementing `AreaContainer` to manage
     * hierarchical or composite structures of areas within a specific context, such as
     * spatial relationships or media layouts.
     */
    var areas: MutableList<Area>
}