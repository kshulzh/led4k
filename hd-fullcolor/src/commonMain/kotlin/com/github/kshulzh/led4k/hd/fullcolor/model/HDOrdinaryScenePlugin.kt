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

package com.github.kshulzh.led4k.hd.fullcolor.model

import com.github.kshulzh.led4k.common.utils.uuid
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class HDOrdinaryScenePlugin(
    var alpha: Int = 255,
    var bgColor: Int = -16777216,
    var bgMode: String = "BgImage",
    var checked: Int = 2,
    var fixedDuration: Int = 2000,
    var frameEffect: Int = 0,
    var frameSpeed: Int = 4,
    var frameType: Int = 0,
    var friday: Int = 0,
    var monday: Int = 0,
    var motleyIndex: Int = 0,
    var playIndex: Int = 0,
    var playMode: String = "FixedTime",
    var playTimes: Int = 1,
    var playeTime: Int = 2,
    var purityColor: Int = 255,
    var purityIndex: Int = 0,
    var saturday: Int = 0,
    var spaceStartTime: String = "00:00:00",
    var spaceStopTime: String = "23:59:59",
    var sunday: Int = 0,
    var thursday: Int = 0,
    var tricolorIndex: Int = 0,
    var tuesday: Int = 0,
    var useSpacifiled: Int = 0,
    var volume: Int = 100,
    var wednesday: Int = 0,
    var __GUID__: String = "{${uuid()}}",
    var __NAME__: String = "unnamed",
    var fileList: FileList = FileList(),
    var nodes: MutableList<Node> = mutableListOf(),
    @Transient
    override var hDControllerPlugin: HDControllerPlugin? = null
) : HDControllerPlugin.Node {
    override var level: String = "2"
    override var type: String = "HD_OrdinaryScene_Plugin"

    init {
        nodes.forEach {
            it.hDOrdinaryScenePlugin = this
        }
    }

    interface Node : BaseNode {
        var hDOrdinaryScenePlugin: HDOrdinaryScenePlugin?
    }

    fun addNode(node: Node) {
        node.hDOrdinaryScenePlugin = this
        nodes.add(node)
    }
}