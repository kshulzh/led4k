/*
 * Copyright (c) 2025-2026. Kirill Shulzhenko
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

import io.github.kshulzh.led4k.hd.fullcolor.effect.HDEffectContinue
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents an HDTextPlugin responsible for managing and configuring various text-related properties
 * and functionalities in a high-definition frame or scene plugin system.
 *
 * This data class implements `HDFramePlugin.Node`, `FileListNode`, and `HDEffectContinue`, allowing it
 * to manage hierarchical structures, file lists, and continuous effects.
 *
 * @property clearEffect The type of effect used to clear the plugin's content.
 * @property clearTime The duration of the clear effect in seconds.
 * @property contentAlign The alignment of the content within the plugin.
 * @property dispEffect The type of display effect used to render the plugin.
 * @property dispTime The duration of the display effect in seconds.
 * @property editBgColor The background color of the edit mode.
 * @property headCloseToTail Indicates whether the "head-close-to-tail" effect is enabled for continuous effects.
 * @property holdTime The duration of the holding state in continuous effects.
 * @property html HTML content managed by the plugin.
 * @property pageCount The total number of pages used by the plugin.
 * @property singleMode The mode of operation for single-page display.
 * @property speedTimeIndex The speed index for animation or time-based effects.
 * @property strokeColor The color of the stroke, if enabled.
 * @property transBgColor Indicates whether the background is transparent.
 * @property useHollow Defines whether hollow text mode is enabled.
 * @property useStroke Defines whether a stroke is applied to the text.
 * @property __GUID__ A globally unique identifier for the plugin instance.
 * @property __NAME__ The name of the plugin instance.
 * @property fileList A list of files associated with the plugin.
 * @property hDFramePlugin The parent `HDFramePlugin` instance associated with this node.
 */
@OptIn(ExperimentalUuidApi::class)
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
    var __GUID__: String = "{${Uuid.random()}}",
    var __NAME__: String = "unnamed",
    override var fileList: FileList = FileList(),
    override var hDFramePlugin: HDFramePlugin? = null
) : HDFramePlugin.Node,
    FileListNode,
    HDEffectContinue {
    /**
     * Represents the hierarchical or priority level of the plugin.
     * This value is used to determine the plugin's relative order or importance
     * within a system or among other plugins.
     */
    override var level: String = "4"
    /**
     * The type descriptor for the plugin instance. This property defines the specific type
     * of the plugin, indicating that it is of type "HD_Text_Plugin".
     */
    override var type: String = "HD_Text_Plugin"
}