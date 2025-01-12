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

package io.github.kshulzh.led4k.common.program

import io.github.kshulzh.led4k.common.area.Area
import io.github.kshulzh.led4k.common.mode.FixedTimesPlayMode
import io.github.kshulzh.led4k.common.mode.PlayMode
import io.github.kshulzh.led4k.common.schedule.DurationSchedule
import io.github.kshulzh.led4k.common.schedule.Schedule
import io.github.kshulzh.led4k.common.utils.Led4kAttributes

/**
 * Implementation of the `SingleProgram` interface, representing a program
 * that manages a single defined behavior or configuration with associated areas.
 *
 * This class allows the definition and manipulation of the core aspects of
 * a single program instance, including its areas, playback mode, schedule,
 * and advanced attributes specific to LED 4K systems.
 *
 * @property areas A mutable list of `Area` objects representing the spatial or logical
 * configurations associated with the program.
 *
 * @property playMode Defines the playback strategy using a `PlayMode` instance. By default,
 * it uses `FixedTimesPlayMode` to specify a fixed number of repetitions.
 *
 * @property schedule Represents the program's scheduling parameters, using a `Schedule`
 * implementation. By default, it uses `DurationSchedule` to specify a defined time range.
 *
 * @property led4kAttributes A mutable map for storing advanced system-specific attributes
 * related to LED 4K systems. This enables additional customization and dynamic property management.
 */
class SingleProgramImpl(
    override var areas: MutableList<Area> = mutableListOf(),
    var playMode: PlayMode = FixedTimesPlayMode(),
    var schedule: Schedule = DurationSchedule(),
    override val led4kAttributes: MutableMap<String, Any?> = mutableMapOf(),
) : SingleProgram, Led4kAttributes