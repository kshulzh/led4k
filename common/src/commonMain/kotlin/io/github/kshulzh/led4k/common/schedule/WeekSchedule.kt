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

package io.github.kshulzh.led4k.common.schedule

/**
 * Represents a weekly schedule where each day can be marked as active or inactive.
 *
 * This class implements the [Schedule] interface, making it a specific type of schedule
 * tailored for weekly-based configurations. Each day of the week is represented by a boolean
 * property, which determines whether the day is included in the schedule.
 *
 * @property monday Indicates whether Monday is active in the schedule.
 * @property tuesday Indicates whether Tuesday is active in the schedule.
 * @property wednesday Indicates whether Wednesday is active in the schedule.
 * @property thursday Indicates whether Thursday is active in the schedule.
 * @property friday Indicates whether Friday is active in the schedule.
 * @property saturday Indicates whether Saturday is active in the schedule.
 * @property sunday Indicates whether Sunday is active in the schedule.
 */
class WeekSchedule(
    val monday: Boolean = true,
    val tuesday: Boolean = true,
    val wednesday: Boolean = true,
    val thursday: Boolean = true,
    val friday: Boolean = true,
    val saturday: Boolean = true,
    val sunday: Boolean = true
) : Schedule