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

package com.github.kshulzh.led4k.hd.fullcolor.program

import com.github.kshulzh.led4k.common.area.Area
import com.github.kshulzh.led4k.common.mode.FixedDurationPlayMode
import com.github.kshulzh.led4k.common.mode.FixedTimesPlayMode
import com.github.kshulzh.led4k.common.mode.PlayMode
import com.github.kshulzh.led4k.common.program.BaseSingleProgram
import com.github.kshulzh.led4k.common.schedule.DurationSchedule
import com.github.kshulzh.led4k.common.schedule.Schedule
import com.github.kshulzh.led4k.common.schedule.WeekSchedule
import com.github.kshulzh.led4k.hd.fullcolor.area.HDOrdinaryScenePluginNode
import com.github.kshulzh.led4k.hd.fullcolor.media.HDMedia
import com.github.kshulzh.led4k.hd.fullcolor.media.HDMediaElement
import com.github.kshulzh.led4k.hd.fullcolor.model.HDOrdinaryScenePlugin
import com.github.kshulzh.led4k.hd.fullcolor.schedule.HDSchedule

class HDSingleProgram : BaseSingleProgram, HDMediaElement {
    override val mediaElements: MutableList<HDMedia>
        get() = areas.filterIsInstance<HDMediaElement>().flatMap {
            it.mediaElements
        }.toMutableList()
    var hdOrdinaryScenePlugin: HDOrdinaryScenePlugin = HDOrdinaryScenePlugin()
        get() {
            areas.forEach {
                if (it is HDOrdinaryScenePluginNode) {
                    field.addNode(it.hdOrdinaryScenePluginNode)
                }
            }
            return field
        }
    override var areas: MutableList<Area> = mutableListOf()
    override var playMode: PlayMode = FixedTimesPlayMode(1)
        set(value) {
            when (value) {
                is FixedTimesPlayMode -> {
                    hdOrdinaryScenePlugin.playMode = "LoopTime"
                    hdOrdinaryScenePlugin.playTimes = value.times
                }

                is FixedDurationPlayMode -> {
                    hdOrdinaryScenePlugin.playMode = "FixedTime"
                    hdOrdinaryScenePlugin.playeTime = (value.ms / 1000).toInt()
                }
            }
            field = value
        }
    override var schedule: Schedule = HDSchedule()
        set(value) {
            val hdSchedule = when (value) {
                is DurationSchedule -> HDSchedule(value)
                is WeekSchedule -> HDSchedule(weekSchedule = value)
                is HDSchedule -> value
                else -> throw RuntimeException("Unsupported ${value::class}")
            }
            hdOrdinaryScenePlugin.monday = hdSchedule.weekSchedule.monday.toInt()
            hdOrdinaryScenePlugin.tuesday = hdSchedule.weekSchedule.tuesday.toInt()
            hdOrdinaryScenePlugin.wednesday = hdSchedule.weekSchedule.wednesday.toInt()
            hdOrdinaryScenePlugin.thursday = hdSchedule.weekSchedule.thursday.toInt()
            hdOrdinaryScenePlugin.friday = hdSchedule.weekSchedule.friday.toInt()
            hdOrdinaryScenePlugin.saturday = hdSchedule.weekSchedule.saturday.toInt()
            hdOrdinaryScenePlugin.sunday = hdSchedule.weekSchedule.sunday.toInt()
            hdOrdinaryScenePlugin.spaceStartTime = hdSchedule.durationSchedule.start.toTime()
            hdOrdinaryScenePlugin.spaceStopTime = hdSchedule.durationSchedule.end.toTime()
            field = hdSchedule
        }
}

fun Boolean.toInt() = if (this) 1 else 0

fun Long.toTime() = "${(1000 / 60 / 60 % 24).toString().padStart(2, '0')}:${
    (this / 1000 / 60 % 60).toString().padStart(2, '0')
}:${(this / 1000 % 60).toString().padStart(2, '0')}"