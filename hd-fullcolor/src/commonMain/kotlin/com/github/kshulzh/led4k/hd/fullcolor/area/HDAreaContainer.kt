/*
 * Copyright (c) 2025. Kirill Shulzhenko
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

package com.github.kshulzh.led4k.hd.fullcolor.area

import com.github.kshulzh.led4k.common.area.Area
import com.github.kshulzh.led4k.common.area.BaseAreaContainer
import com.github.kshulzh.led4k.hd.fullcolor.media.HDMedia
import com.github.kshulzh.led4k.hd.fullcolor.media.HDMediaElement
import com.github.kshulzh.led4k.hd.fullcolor.model.HDFramePlugin
import com.github.kshulzh.led4k.hd.fullcolor.model.HDOrdinaryScenePlugin

class HDAreaContainer(
    val hdFramePlugin: HDFramePlugin = HDFramePlugin(),
    val hdFramePluginNodes: MutableList<HDFramePluginNode> = mutableListOf()
) : BaseAreaContainer, HDMediaElement, HDOrdinaryScenePluginNode {
    override val mediaElements: MutableList<HDMedia>
        get() = hdFramePluginNodes.filterIsInstance<HDMediaElement>().flatMap {
            it.mediaElements
        }.toMutableList()
    override var x: Int
        get() = hdFramePlugin.x
        set(value) {
            hdFramePlugin.x = value
        }
    override var y: Int
        get() = hdFramePlugin.y
        set(value) {
            hdFramePlugin.y = value
        }
    override var width: Int
        get() = hdFramePlugin.width
        set(value) {
            hdFramePlugin.width = value
        }
    override var height: Int
        get() = hdFramePlugin.height
        set(value) {
            hdFramePlugin.height = value
        }
    override var areas: MutableList<Area> = mutableListOf()
    override fun <T : Area> addArea(e: T): T {
        return super.addArea(e).also {
            if (e is HDFramePluginNode) {
                hdFramePluginNodes.add(e)
                hdFramePlugin.addNode(e.hDFramePluginNode)
            }
        }
    }

    override val hdOrdinaryScenePluginNode: HDOrdinaryScenePlugin.Node
        get() = hdFramePlugin
}