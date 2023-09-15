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
class HD_Controller_Plugin : HD_Node() {
    override var name: String = "HD_Controller_Plugin"

    @HDAttribute("AppVersion", "")
    lateinit var appVersion: String

    @HDAttribute("DeviceModel", "")
    lateinit var deviceModel: String

    @HDAttribute("Height", "0")
    lateinit var height: String

    @HDAttribute("InsertProject", "0")
    lateinit var insertProject: String

    @HDAttribute("Rotation", "0")
    lateinit var rotation: String

    @HDAttribute("Stretch", "0")
    lateinit var stretch: String

    @HDAttribute("SvnVersion", "")
    lateinit var svnVersion: String

    @HDAttribute("TimeZone", "0")
    lateinit var timeZone: String

    @HDAttribute("Width", "0")
    lateinit var width: String

    @HDAttribute("ZoomModulus", "4")
    lateinit var zoomModulus: String

    @HDAttribute("__NAME__", "Unnamed")
    lateinit var __NAME__: String

    @HDAttribute("mimiScreen", "0")
    lateinit var mimiScreen: String

    @HDList("communication")
    lateinit var communication: List<Communication>

    @HDNodes
    lateinit var nodes: List<HD_Node>
}