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

@HDElement
class HD_Text_Plugin : HD_Node() {
    override var name: String = "HD_Text_Plugin"

    @HDAttribute("Checked", "2")
    lateinit var checked: String

    @HDAttribute("ClearEffect", "0")
    lateinit var clearEffect: String

    @HDAttribute("ClearTime", "4")
    lateinit var clearTime: String

    @HDAttribute("ColorfulTextEnable", "0")
    lateinit var colorfulTextEnable: String

    @HDAttribute("ColorfulTextIndex", "3")
    lateinit var colorfulTextIndex: String

    @HDAttribute("ColorfulTextSelect", "://images/Colorful/static.png")
    lateinit var colorfulTextSelect: String

    @HDAttribute("ColorfulTextSpeed", "0")
    lateinit var colorfulTextSpeed: String

    @HDAttribute("ContentAlign", "132")
    lateinit var contentAlign: String

    @HDAttribute("DispEffect", "0")
    lateinit var dispEffect: String

    @HDAttribute("DispTime", "4")
    lateinit var dispTime: String

    @HDAttribute("EditBgColor", "0")
    lateinit var editBgColor: String

    @HDAttribute("HeadCloseToTail", "1")
    lateinit var headCloseToTail: String

    @HDAttribute("HoldTime", "50")
    lateinit var holdTime: String

    @HDAttribute("Html", "")
    lateinit var html: String

    @HDAttribute("PageCount", "1")
    lateinit var pageCount: String

    @HDAttribute("SingleMode", "0")
    lateinit var singleMode: String

    @HDAttribute("SpeedTimeIndex", "4")
    lateinit var speedTimeIndex: String

    @HDAttribute("StrokeColor", "65280")
    lateinit var strokeColor: String

    @HDAttribute("TransBgColor", "1")
    lateinit var transBgColor: String

    @HDAttribute("UseHollow", "0")
    lateinit var useHollow: String

    @HDAttribute("UseStroke", "0")
    lateinit var useStroke: String

    @HDAttribute("__GUID__", "")
    lateinit var __GUID__: String

    @HDAttribute("__NAME__", "Unnamed")
    lateinit var __NAME__: String

    @HDList("__FileList__")
    lateinit var __FileList__: List<FileElement>
}