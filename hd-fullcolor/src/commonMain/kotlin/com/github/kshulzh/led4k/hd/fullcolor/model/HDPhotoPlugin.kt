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
import com.github.kshulzh.led4k.hd.fullcolor.effect.HDEffect

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class HDPhotoPlugin(
    var checked: Int = 2,
    override var clearEffect: Int = 0,
    override var clearTime: Int = 4,
    var convertImage: String = "/usr/1.png",
    override var dispEffect: Int = 0,
    override var dispTime: Int = 4,
    override var holdTime: Int = 50,
    var keepRatio: Int = 0,
    var preloadFilePath: String = "/usr/1.png",
    var speedTimeIndex: Int = 4,
    var __GUID__: String = "{${uuid()}}",
    var __NAME__: String = "unnamed",
    override var fileList: FileList = FileList(),
    @Transient
    override var hDFramePlugin: HDFramePlugin? = null
) : HDFramePlugin.Node, FileListNode, HDEffect {
    override var level: String = "4"
    override var type: String = "HD_Photo_Plugin"
}