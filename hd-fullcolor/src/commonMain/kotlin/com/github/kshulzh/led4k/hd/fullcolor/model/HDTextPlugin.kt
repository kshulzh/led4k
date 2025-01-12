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

package com.github.kshulzh.led4k.hd.fullcolor.model

import com.github.kshulzh.led4k.common.utils.uuid
import com.github.kshulzh.led4k.hd.fullcolor.effect.HDEffectContinue
import kotlinx.serialization.Transient

data class HDTextPlugin(
    override var clearEffect: Int = 0,
    override var clearTime: Int = 4,
    var contentAlign: Int = 132,
    override var dispEffect: Int = 0,
    override var dispTime: Int = 4,
    var editBgColor: Int = 0,
    override var headCloseToTail: Int = 1,
    override var holdTime: Int = 50,
    var html: String = "",
    var pageCount: Int = 1,
    var singleMode: Int = 0,
    var speedTimeIndex: Int = 4,
    var strokeColor: Int = 65280,
    var transBgColor: Int = 1,
    var useHollow: Int = 0,
    var useStroke: Int = 0,
    var __GUID__: String = "{${uuid()}}",
    var __NAME__: String = "unnamed",
    override var fileList: FileList = FileList(),
    @Transient
    override var hDFramePlugin: HDFramePlugin? = null
) : HDFramePlugin.Node, FileListNode, HDEffectContinue {
    override var level: String = "4"
    override var type: String = "HD_Text_Plugin"
}