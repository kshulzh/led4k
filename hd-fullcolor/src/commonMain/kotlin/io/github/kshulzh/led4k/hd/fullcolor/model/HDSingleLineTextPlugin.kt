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

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents a plugin for single-line text in HD displays. This class provides various
 * configurations to customize how text content is displayed, including alignment, color,
 * font, speed, and effects.
 *
 * It implements [HDFramePlugin.Node], [FileListNode], and [HDEffectContinue], allowing it
 * to be integrated with other plugins, manage associated files, and define transition effects.
 *
 * Primary properties include:
 * - Text content and formatting options such as alignment, color, font name, and font size.
 * - Animation options like display effects, clear effects, speed, and timing.
 * - Configuration for additional visual elements such as stroke, hollowing, and transparent backgrounds.
 *
 * The class is also associated with a unique identifier (__GUID__) and a default name (__NAME__).
 */
@OptIn(ExperimentalUuidApi::class)
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
    var __GUID__: String = "{${Uuid.random()}}",
    var __NAME__: String = "unnamed",
    override var fileList: FileList = FileList(),
    override var hDFramePlugin: HDFramePlugin? = null
) : HDFramePlugin.Node,
    FileListNode,
    io.github.kshulzh.led4k.hd.fullcolor.effect.HDEffectContinue {
    /**
     * Represents the level of the plugin, which determines its hierarchical or functional
     * categorization within the system. This value may influence the behavior or priority of
     * the plugin in certain contexts.
     */
    override var level: String = "4"
    /**
     * Specifies the type identifier for the plugin.
     *
     * This variable is used to distinguish the specific implementation type of the plugin,
     * which, in this case, is designated as "HD_SingleLineText_Plugin". It ensures that the
     * plugin instance can be correctly identified and utilized within the system that processes
     * or interacts with such entities.
     *
     * The value of this property should not be modified unless there is a specific need
     * to redefine the plugin's type for compatibility or functionality purposes.
     */
    override var type: String = "HD_SingleLineText_Plugin"
}