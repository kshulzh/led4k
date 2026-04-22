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

package io.github.kshulzh.led4k.hd.fullcolor.schedule

import io.github.kshulzh.led4k.common.schedule.DurationSchedule
import io.github.kshulzh.led4k.common.schedule.Schedule
import io.github.kshulzh.led4k.common.schedule.WeekSchedule

/**
 * Combines both a duration-based schedule and a weekly schedule into a single unified schedule.
 *
 * This class provides a compound scheduling mechanism by incorporating two distinct types of schedules:
 * - A [DurationSchedule], which specifies a time-based range.
 * - A [WeekSchedule], which defines active or inactive days of the week.
 *
 * The [HDSchedule] class implements the [Schedule] interface, making it compatible with other scheduling-related
 * components. The two schedule types can be used together to provide fine-grained control over scheduling logic.
 *
 * @property durationSchedule Represents a schedule with a specific duration defined by start and end times.
 * @property weekSchedule Represents a weekly schedule, where each day can be marked as active or inactive.
 */
class HDSchedule(
    val durationSchedule: DurationSchedule = DurationSchedule(),
    val weekSchedule: WeekSchedule = WeekSchedule(),
) : Schedule