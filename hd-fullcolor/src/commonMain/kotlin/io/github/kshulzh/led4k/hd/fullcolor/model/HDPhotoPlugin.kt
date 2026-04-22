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

import io.github.kshulzh.led4k.hd.fullcolor.effect.HDEffect
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents the HDPhotoPlugin class used for handling high-definition photo-related functionalities
 * in an HDFramePlugin framework. This class integrates with file management, effects handling,
 * and other node-based functionalities.
 *
 * This class implements the following interfaces:
 * - [HDFramePlugin.Node]: Represents a node in the HDFramePlugin framework.
 * - [FileListNode]: Provides file list management capabilities.
 * - [HDEffect]: Manages display and clearing effects as well as their durations.
 *
 * @property checked Determines whether the plugin is enabled or checked, with the default value of 2.
 * @property clearEffect Specifies the type of clear effect applied, inherited from [HDEffect].
 * @property clearTime Specifies the duration of the clear effect in seconds, inherited from [HDEffect].
 * @property convertImage File path to the image being converted within the plugin.
 * @property dispEffect Specifies the type of display effect applied, inherited from [HDEffect].
 * @property dispTime Specifies the duration of the display effect in seconds, inherited from [HDEffect].
 * @property holdTime Specifies the time to hold the current display state before changes, inherited from [HDEffect].
 * @property keepRatio Indicates whether to maintain the aspect ratio of images, with the default value of 0.
 * @property preloadFilePath File path used for preloading images.
 * @property speedTimeIndex Index used for determining the playback or transition speed.
 * @property __GUID__ Unique globally-identifier string for the plugin instance.
 * @property __NAME__ Name of the plugin instance, defaults to "unnamed".
 * @property fileList Collection of file items associated with the plugin, inherited from [FileListNode].
 * @property hDFramePlugin Reference to the parent [HDFramePlugin] this plugin is associated with, inherited from [HDFramePlugin.Node].
 * @property level Represents the hierarchical level of this node within the HDFramePlugin framework, defaulting to "4".
 * @property type Defines the type of the plugin, defaulting to "HD_Photo_Plugin".
 */
@OptIn(ExperimentalUuidApi::class)
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
    var __GUID__: String = "{${Uuid.random()}}",
    var __NAME__: String = "unnamed",
    override var fileList: FileList = FileList(),
    override var hDFramePlugin: HDFramePlugin? = null
) : HDFramePlugin.Node,
    FileListNode, HDEffect {
    /**
     * Represents the level of the HDPhotoPlugin.
     * This property defines the hierarchical or categorical position of the plugin within its structure or usage context.
     */
    override var level: String = "4"
    /**
     * Represents the type of the plugin used in the HDPhotoPlugin class.
     * This variable identifies the specific type of the plugin as "HD_Photo_Plugin".
     * It can be overridden to indicate different plugin types if needed.
     */
    override var type: String = "HD_Photo_Plugin"
}