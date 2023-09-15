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
import com.github.kshulzh.led4k.hd.ksp.annotations.HDNodes

@HDElement
class HD_Frame_Plugin : HD_Node() {
    override var name: String = "HD_Frame_Plugin"

    @HDAttribute("Alpha", "255")
    lateinit var alpha: String

    @HDAttribute("Checked", "2")
    lateinit var checked: String

    @HDAttribute("ChildType", "")
    lateinit var childType: String

    @HDAttribute("FrameSpeed", "4")
    lateinit var frameSpeed: String

    @HDAttribute("FrameType", "0")
    lateinit var frameType: String

    @HDAttribute("Height", "64")
    lateinit var height: String

    @HDAttribute("Index", "0")
    lateinit var index: String

    @HDAttribute("LockArea", "0")
    lateinit var lockArea: String

    @HDAttribute("MotleyIndex", "0")
    lateinit var motleyIndex: String

    @HDAttribute("PurityColor", "255")
    lateinit var purityColor: String

    @HDAttribute("PurityIndex", "0")
    lateinit var purityIndex: String

    @HDAttribute("TricolorIndex", "0")
    lateinit var tricolorIndex: String

    @HDAttribute("Width", "0")
    lateinit var width: String

    @HDAttribute("X", "0")
    lateinit var x: String

    @HDAttribute("Y", "0")
    lateinit var y: String

    @HDAttribute("__GUID__", "")
    lateinit var __GUID__: String

    @HDAttribute("__NAME__", "Unnamed")
    lateinit var __NAME__: String

    @HDNodes
    lateinit var nodes: List<HD_Node>
}