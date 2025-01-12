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
import com.github.kshulzh.led4k.hd.fullcolor.model.HDOrdinaryScenePlugin.Node
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class HDFramePlugin(
    var alpha: Int = 255,
    var checked: Int = 2,
    var childType: String = "HD_Photo_Plugin",
    var frameSpeed: Int = 4,
    var frameType: Int = 0,
    var height: Int = 128,
    var index: Int = 0,
    var lockArea: Int = 0,
    var motleyIndex: Int = 0,
    var purityColor: Int = 255,
    var purityIndex: Int = 0,
    var tricolorIndex: Int = 0,
    var width: Int = 128,
    var x: Int = 0,
    var y: Int = 0,
    var __GUID__: String = "{${uuid()}}",
    var __NAME__: String = "unnamed",
    var nodes: MutableList<Node> = mutableListOf(),
    @Transient
    override var hDOrdinaryScenePlugin: HDOrdinaryScenePlugin? = null
) : Node {
    override var level: String = "3"
    override var type: String = "HD_Frame_Plugin"

    init {
        nodes.forEach {
            it.hDFramePlugin = this
        }
    }

    interface Node : BaseNode {
        var hDFramePlugin: HDFramePlugin?
    }

    fun addNode(node: Node) {
        node.hDFramePlugin = this
        nodes.add(node)
    }
}
