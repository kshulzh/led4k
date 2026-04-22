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

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Represents a plugin for ordinary scenes in an HD controller system. This plugin allows
 * configuring various aspects of scene display and behavior, such as background settings,
 * playback modes, schedule, and file lists for the scene.
 *
 * @property alpha The transparency level of the scene, ranging from 0 (completely transparent)
 *                 to 255 (fully opaque).
 * @property bgColor The background color of the scene, represented as an integer color code.
 * @property bgMode The mode for displaying the background, e.g., "BgImage".
 * @property checked The checked status of the plugin.
 * @property fixedDuration The fixed duration (in milliseconds) for the scene playback.
 * @property frameEffect The effect applied to frames within the scene.
 * @property frameSpeed The speed of frame transitions.
 * @property frameType The type of the frame for the scene.
 * @property friday The playback status for Friday.
 * @property monday The playback status for Monday.
 * @property motleyIndex The index for a motley color scheme.
 * @property playIndex The index for the playback sequence.
 * @property playMode The playback mode, e.g., "FixedTime" or other supported modes.
 * @property playTimes The number of times the scene is played.
 * @property playeTime The playback time in seconds.
 * @property purityColor The color purity level for the scene.
 * @property purityIndex The index for the purity settings.
 * @property saturday The playback status for Saturday.
 * @property spaceStartTime The start time for the playback window in "HH:mm:ss" format.
 * @property spaceStopTime The stop time for the playback window in "HH:mm:ss" format.
 * @property sunday The playback status for Sunday.
 * @property thursday The playback status for Thursday.
 * @property tricolorIndex The index for a tricolor scheme.
 * @property tuesday The playback status for Tuesday.
 * @property useSpacifiled Indicates whether specific slots are used for the scene.
 * @property volume The playback volume, ranging from 0 (muted) to 100 (full volume).
 * @property wednesday The playback status for Wednesday.
 * @property __GUID__ A unique identifier for the plugin, generated as a UUID.
 * @property __NAME__ The name of the plugin instance.
 * @property fileList A list of files associated with the scene.
 * @property nodes A mutable list of nodes associated with this plugin.
 * @property hDControllerPlugin The parent HD controller plugin associated with this scene plugin.
 */
data class HDOrdinaryScenePlugin @OptIn(ExperimentalUuidApi::class) constructor(
    var alpha: Int = 255,
    var bgColor: Int = -16777216,
    var bgMode: String = "BgImage",
    var checked: Int = 2,
    var fixedDuration: Int = 2000,
    var frameEffect: Int = 0,
    var frameSpeed: Int = 4,
    var frameType: Int = 0,
    var friday: Int = 0,
    var monday: Int = 0,
    var motleyIndex: Int = 0,
    var playIndex: Int = 0,
    var playMode: String = "FixedTime",
    var playTimes: Int = 1,
    var playeTime: Int = 2,
    var purityColor: Int = 255,
    var purityIndex: Int = 0,
    var saturday: Int = 0,
    var spaceStartTime: String = "00:00:00",
    var spaceStopTime: String = "23:59:59",
    var sunday: Int = 0,
    var thursday: Int = 0,
    var tricolorIndex: Int = 0,
    var tuesday: Int = 0,
    var useSpacifiled: Int = 0,
    var volume: Int = 100,
    var wednesday: Int = 0,
    var __GUID__: String = "{${Uuid.random()}}",
    var __NAME__: String = "unnamed",
    var fileList: FileList = FileList(),
    var nodes: MutableList<Node> = mutableListOf(),
    override var hDControllerPlugin: HDControllerPlugin? = null
) : HDControllerPlugin.Node {
    /**
     * Indicates the hierarchical level of the plugin within its associated system or structure.
     * Used to define or classify the plugin's role or priority in the execution or configuration process.
     */
    override var level: String = "2"
    /**
     * Represents the type of the HDOrdinaryScenePlugin. This property is used to identify
     * the specific implementation type of the plugin. It is overridden to provide a
     * default value specific to this plugin type.
     */
    override var type: String = "HD_OrdinaryScene_Plugin"

    init {
        nodes.forEach {
            it.hDOrdinaryScenePlugin = this
        }
    }

    /**
     * Represents a node in a hierarchical structure. This interface extends the BaseNode
     * and provides additional functionality for associating a node with an instance of
     * HDOrdinaryScenePlugin.
     */
    interface Node : BaseNode {
        /**
         * Represents an optional instance of the `HDOrdinaryScenePlugin` associated with this node.
         *
         * The `hDOrdinaryScenePlugin` variable allows linking this node to a corresponding `HDOrdinaryScenePlugin`,
         * enabling scenes to retain a hierarchical structure or establish relationships among components.
         *
         * When assigning a value to this variable, the `HDOrdinaryScenePlugin` instance may automatically configure
         * certain properties or associate itself back with this node if applicable.
         */
        var hDOrdinaryScenePlugin: HDOrdinaryScenePlugin?
    }

    /**
     * Adds a node to the current HDOrdinaryScenePlugin instance and associates the node with the plugin.
     *
     * @param node The node to be added. The provided node will have its `hDOrdinaryScenePlugin` property
     *             set to reference the current HDOrdinaryScenePlugin instance before being added to the `nodes` list.
     */
    fun addNode(node: Node) {
        node.hDOrdinaryScenePlugin = this
        nodes.add(node)
    }
}