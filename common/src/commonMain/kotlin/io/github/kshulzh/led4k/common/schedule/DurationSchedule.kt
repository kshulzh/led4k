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
 * Represents a schedule with a specific duration defined by a start and end time.
 *
 * The schedule duration is specified using millisecond values, where `start` indicates
 * the beginning of the schedule and `end` specifies the end of the schedule. By default,
 * the schedule spans an entire day from midnight (00:00:00) to the last millisecond
 * before midnight of the subsequent day (23:59:59.999).
 *
 * Implements the [Schedule] interface, making it a specific type of schedule.
 *
 * @property start The start time of the schedule in milliseconds.
 * @property end The end time of the schedule in milliseconds.
 */
class DurationSchedule(
    var start: Long = 0,
    var end: Long = 24 * 60 * 60 * 1000 - 1
) : Schedule