/*
 *   Copyright (c) 2023. Kyrylo Shulzhenko
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.github.kshulzh.led4k.hd.model.elements

import com.github.kshulzh.led4k.hd.ksp.annotations.HDAttribute
import com.github.kshulzh.led4k.hd.ksp.annotations.HDElement
import com.github.kshulzh.led4k.hd.ksp.annotations.HDList
import com.github.kshulzh.led4k.hd.ksp.annotations.HDNodes

@HDElement
class HD_OrdinaryScene_Plugin : HD_Node() {
    override var name: String = "HD_OrdinaryScene_Plugin"

    @HDAttribute("Alpha", "255")
    lateinit var alpha: String

    @HDAttribute("BgColor", "-16777216")
    lateinit var bgColor: String

    @HDAttribute("BgMode", "BgImage")
    lateinit var bgMode: String

    @HDAttribute("Checked", "2")
    lateinit var checked: String

    @HDAttribute("FixedDuration", "2000")
    lateinit var fixedDuration: String

    @HDAttribute("FrameEffect", "0")
    lateinit var frameEffect: String

    @HDAttribute("FrameSpeed", "4")
    lateinit var frameSpeed: String

    @HDAttribute("FrameType", "0")
    lateinit var frameType: String

    @HDAttribute("Friday", "0")
    lateinit var friday: String

    @HDAttribute("Monday", "0")
    lateinit var monday: String

    @HDAttribute("MotleyIndex", "0")
    lateinit var motleyIndex: String

    @HDAttribute("PlayIndex", "0")
    lateinit var playIndex: String

    @HDAttribute("PlayMode", "FixedTime")
    lateinit var playMode: String

    @HDAttribute("PlayTimes", "1")
    lateinit var playTimes: String

    @HDAttribute("PlayeTime", "2")
    lateinit var playeTime: String

    @HDAttribute("PurityColor", "255")
    lateinit var purityColor: String

    @HDAttribute("PurityIndex", "0")
    lateinit var purityIndex: String

    @HDAttribute("Saturday", "0")
    lateinit var saturday: String

    @HDAttribute("SpaceStartTime", "00:00:00")
    lateinit var spaceStartTime: String

    @HDAttribute("SpaceStopTime", "23:59:59")
    lateinit var spaceStopTime: String

    @HDAttribute("Sunday", "0")
    lateinit var sunday: String

    @HDAttribute("Thursday", "0")
    lateinit var thursday: String

    @HDAttribute("TricolorIndex", "0")
    lateinit var tricolorIndex: String

    @HDAttribute("Tuesday", "0")
    lateinit var tuesday: String

    @HDAttribute("UseSpacifiled", "0")
    lateinit var useSpacifiled: String

    @HDAttribute("Volume", "100")
    lateinit var volume: String

    @HDAttribute("Wednesday", "0")
    lateinit var wednesday: String

    @HDAttribute("__GUID__", "")
    lateinit var __GUID__: String

    @HDAttribute("__NAME__", "Unnamed")
    lateinit var __NAME__: String

    @HDList("__FileList__")
    lateinit var __FileList__: List<FileElement>

    @HDNodes
    lateinit var nodes: List<HD_Node>
}