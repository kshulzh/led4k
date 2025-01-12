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
import kotlinx.serialization.Transient

data class HDVideoPlugin(
    var audioCodec: String = "AAC",
    var audioCodecID: String = "mp4a-40-2",
    var audioCommercial: String = "AAC",
    var bitRate: Int = 8687,
    var byArea: Int = 0,
    var byTime: Int = 0,
    var checked: Int = 2,
    var codecID: String = "mp42",
    var encoding: Int = 1,
    var fHeight: Int = 128,
    var FPS: Int = 60,
    var fWidth: Int = 128,
    var fileMd5: String = "fb1fb45f9a511e22db5e363d694aace2",
    var hardwareAccele: Int = 1,
    var playTimes: Int = 1,
    var resolution: Int = 0,
    var sourceFileTime: Int = 5,
    var streamFormat: String = "MPEG-4",
    var useRatio: Int = 0,
    var useTranscoding: Int = 0,
    var vShotByArea: Int = 0,
    var vShotByTime: Int = 0,
    var vShotEnd: Int = 0,
    var vShotHeight: Int = 0,
    var vShotStart: Int = 0,
    var vShotWidth: Int = 0,
    var vShotX: Int = 0,
    var vShotY: Int = 0,
    var videoFormat: String = "AVC",
    var videoProfile: String = "Main@L3.1",
    var videoQuality: Int = 2,
    var volume: Int = 100,
    var __GUID__: String = "{${uuid()}}",
    var __NAME__: String = "unnamed",
    override var fileList: FileList = FileList(),
    @Transient
    override var hDFramePlugin: HDFramePlugin? = null
) : HDFramePlugin.Node, FileListNode {
    override var level: String = "4"
    override var type: String = "HD_Video_Plugin"
}