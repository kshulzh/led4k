/*
 * Copyright (c) 2024-2026. Kirill Shulzhenko
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

package io.github.kshulzh.led4k.hd.fullcolor.model

import kotlinx.serialization.Transient
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents an HD video plugin configuration used for managing and describing properties
 * of an HD video, including codec information, resolution, frame rate, and various other
 * attributes related to encoding and playback.
 *
 * This class is a data model for specifying settings and metadata for handling HD video content.
 * It implements the `HDFramePlugin.Node` and `FileListNode` interfaces to integrate with
 * other plugin and file-related systems.
 *
 * Primary Features:
 * - Audio and video codec configurations, including audio and video profiles.
 * - Resolution and frame rate management.
 * - Transcoding settings and hardware acceleration options.
 * - Metadata fields such as GUID, playback settings, and file identifiers.
 *
 * This class also contains properties for managing regions, shot areas, and duration-based
 * video settings. The object is designed to be compatible with plugins in an HD multimedia
 * framework.
 */
@OptIn(ExperimentalUuidApi::class)
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
    var notTranscoding: Int = 1,
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
    var __GUID__: String = "{${Uuid.random()}}",
    var __NAME__: String = "unnamed",
    override var fileList: FileList = FileList(),
    @Transient
    override var hDFramePlugin: HDFramePlugin? = null
) : HDFramePlugin.Node,
    FileListNode {
    /**
     * Represents the hierarchical level of the HDVideoPlugin within a structure or system.
     * The level is defined as a string, enabling flexible representation of the plugin's position
     * in the hierarchy.
     */
    override var level: String = "4"
    /**
     * Represents the type of the HD video plugin. This property indicates the classification or
     * designation of the plugin within the system, which helps in identifying its specific purpose or functionality.
     * It is initialized to "HD_Video_Plugin" by default.
     */
    override var type: String = "HD_Video_Plugin"
}