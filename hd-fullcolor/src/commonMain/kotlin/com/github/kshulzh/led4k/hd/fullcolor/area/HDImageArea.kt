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

package com.github.kshulzh.led4k.hd.fullcolor.area

import com.github.kshulzh.led4k.common.area.BaseImageArea
import com.github.kshulzh.led4k.common.effect.BasicEffects
import com.github.kshulzh.led4k.common.effect.ContinuousEffect
import com.github.kshulzh.led4k.common.effect.Effect
import com.github.kshulzh.led4k.common.media.Media
import com.github.kshulzh.led4k.hd.fullcolor.media.HDMedia
import com.github.kshulzh.led4k.hd.fullcolor.media.HDMediaElement
import com.github.kshulzh.led4k.hd.fullcolor.media.of
import com.github.kshulzh.led4k.hd.fullcolor.media.ofMedia
import com.github.kshulzh.led4k.hd.fullcolor.model.FileItem
import com.github.kshulzh.led4k.hd.fullcolor.model.HDFramePlugin
import com.github.kshulzh.led4k.hd.fullcolor.model.HDOrdinaryScenePlugin
import com.github.kshulzh.led4k.hd.fullcolor.model.HDPhotoPlugin
import com.github.kshulzh.led4k.hd.fullcolor.model.HDSingleLineTextPlugin
import com.github.kshulzh.led4k.media.GraphicMedia
import com.github.kshulzh.led4k.media.UriMedia

class HDImageArea : BaseImageArea, HDMediaElement, HDOrdinaryScenePluginNode, HDFramePluginNode {
    val hdFramePlugin: HDFramePlugin = HDFramePlugin()
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
    override var mediaElements: MutableList<HDMedia> = mutableListOf()
    override var effect: Effect = BasicEffects.DEFAULT
    override lateinit var media: Media
    override val hdOrdinaryScenePluginNode: HDOrdinaryScenePlugin.Node
        get() {
            hdFramePlugin.nodes.clear()
            hdFramePlugin.addNode(hDFramePluginNode.also {
                hdFramePlugin.childType = it.type
            })

            return hdFramePlugin
        }
    override val hDFramePluginNode: HDFramePlugin.Node
        get() {
            val m: HDMedia = when (media) {
                is HDMedia -> media as HDMedia
                is UriMedia -> HDMedia.of((media as UriMedia).uri)
                is GraphicMedia -> HDMedia.ofMedia(media as GraphicMedia)
                else -> throw RuntimeException("Unsupported class ${media::class}")
            }
            val plugin = when (effect) {
                is ContinuousEffect, is ContinuousEffect.ContinuousEffects -> HDSingleLineTextPlugin()
                else -> HDPhotoPlugin()
            }
            val key = when (effect) {
                is ContinuousEffect, is ContinuousEffect.ContinuousEffects -> "SingleLineText"
                else -> "Photo"
            }
            mediaElements = mutableListOf(m)
            plugin.fileList.clear()
            mediaElements.forEach {
                plugin.fileList.add(
                    FileItem(
                        it.md5,
                        "${it.md5}.${it.extension}",
                        key
                    )
                )
            }
            plugin.setEffect(effect)
            return plugin
        }
}
