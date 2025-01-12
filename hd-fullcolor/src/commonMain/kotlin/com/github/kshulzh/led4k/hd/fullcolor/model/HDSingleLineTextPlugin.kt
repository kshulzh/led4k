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
import com.github.kshulzh.led4k.hd.fullcolor.effect.HDEffectContinue

data class HDSingleLineTextPlugin(
    var byCount: Int = 1,
    var byTime: Int = 300,
    override var clearEffect: Int = 25,
    override var clearTime: Int = 4,
    var contentAlign: Int = 132,
    var contentHAlign: Int = 1,
    override var dispEffect: Int = 26,
    override var dispTime: Int = 4,
    var editBgColor: Int = 0,
    override var headCloseToTail: Int = 1,
    override var holdTime: Int = 50,
    var html: String = "",
    var pageCount: Int = 1,
    var playText: String = "TU9ORVkgVFJBTlNGRVJTOiBXRUxTRU5ELCBNT1NTVCwgTVkgVFJBTlNGRVIuICAgIA==",
    var playType: String = "ByCount",
    var singleMode: Int = 1,
    var speed: Int = 4,
    var speedTimeIndex: Int = 4,
    var strokeColor: Int = 65280,
    var textColor: String = "#ffffff",
    var textFontName: String = "Arial Narrow",
    var textFontSize: Int = 13,
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
    override var type: String = "HD_SingleLineText_Plugin"
}