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
class HD_Photo_Plugin : HD_Node() {
    override var name: String = "HD_Photo_Plugin"

    @HDAttribute("Checked", "2")
    lateinit var checked: String

    @HDAttribute("ClearEffect", "0")
    lateinit var clearEffect: String

    @HDAttribute("ClearTime", "4")
    lateinit var clearTime: String

    @HDAttribute("ConvertImage", "")
    lateinit var convertImage: String

    @HDAttribute("DispEffect", "0")
    lateinit var dispEffect: String

    @HDAttribute("DispTime", "4")
    lateinit var dispTime: String

    @HDAttribute("HoldTime", "50")
    lateinit var holdTime: String

    @HDAttribute("KeepRatio", "0")
    lateinit var keepRatio: String

    @HDAttribute("PreloadFilePath", "")
    lateinit var preloadFilePath: String

    @HDAttribute("SpeedTimeIndex", "4")
    lateinit var speedTimeIndex: String

    @HDAttribute("__GUID__:", "")
    lateinit var __GUID__: String

    @HDAttribute("__NAME__", "Unnamed")
    lateinit var __NAME__: String

    @HDList("__FileList__")
    lateinit var __FileList__: List<FileElement>
}