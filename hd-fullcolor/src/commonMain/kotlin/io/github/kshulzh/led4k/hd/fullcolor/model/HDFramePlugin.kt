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

import io.github.kshulzh.led4k.hd.fullcolor.model.HDOrdinaryScenePlugin.Node
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents a plugin for managing HD frames within a hierarchical structure.
 * This plugin is responsible for configuring various properties such as frame behavior,
 * dimensions, position, transparency, and associated nodes.
 *
 * @property alpha The transparency level of the frame, ranging from 0 (completely transparent) to 255 (fully opaque).
 * @property checked Represents whether the frame is in a checked state, useful for toggling its selection or activation status.
 * @property childType A string indicating the child type of the plugin, defaulting to "HD_Photo_Plugin".
 * @property frameSpeed The speed at which frame animations or transitions occur.
 * @property frameType Defines the type of the frame, used for categorization or specific frame behavior.
 * @property height Height of the frame in pixels.
 * @property index An integer indicating the index or position of the frame in its collection.
 * @property lockArea Represents whether a specific area of the frame is locked, preventing modifications.
 * @property motleyIndex Index representing a motley color effect or scheme applied to the frame.
 * @property purityColor The color purity level used for the frame.
 * @property purityIndex Index associated with the purity setting for the frame.
 * @property tricolorIndex Index for a multicolor or tricolor scheme applied to the frame.
 * @property width Width of the frame in pixels.
 * @property x Horizontal position of the frame within its container.
 * @property y Vertical position of the frame within its container.
 * @property __GUID__ A unique identifier for the frame plugin, automatically assigned as a UUID.
 * @property __NAME__ The name of the frame plugin instance.
 * @property nodes A mutable list of associated nodes linked to this frame plugin.
 * @property hDOrdinaryScenePlugin An optional reference to the parent HDOrdinaryScenePlugin, establishing a hierarchical relationship.
 */
@OptIn(ExperimentalUuidApi::class)
data class HDFramePlugin(
    var alpha: Int = 255,
    var checked: Int = 2,
    var childType: String = "HD_Photo_Plugin",
    var frameSpeed: Int = 4,
    var frameType: Int = 0,
    var height: Int = 128,
    var index: Int = 0,
    var lockArea: Int = 0,
    var motleyIndex: Int = 0,
    var purityColor: Int = 255,
    var purityIndex: Int = 0,
    var tricolorIndex: Int = 0,
    var width: Int = 128,
    var x: Int = 0,
    var y: Int = 0,
    var __GUID__: String = "{${Uuid.random()}}",
    var __NAME__: String = "unnamed",
    var nodes: MutableList<Node> = mutableListOf(),
    override var hDOrdinaryScenePlugin: HDOrdinaryScenePlugin? = null
) : Node {
    /**
     * Represents the level of the HD frame plugin within a hierarchy or configuration.
     * This property is overridden from the base class to provide plugin-specific level information.
     * The default value is set to "3".
     */
    override var level: String = "3"
    /**
     * Represents the type of the plugin within the HDFramePlugin component.
     * This variable is used to define and identify the specific plugin type.
     *
     * Default value: "HD_Frame_Plugin".
     */
    override var type: String = "HD_Frame_Plugin"

    init {
        nodes.forEach {
            it.hDFramePlugin = this
        }
    }

    /**
     * Represents a generic node in the structure. Nodes can be part of a hierarchical
     * system and may hold a reference to an associated [HDFramePlugin].
     *
     * This interface extends [BaseNode], inheriting properties that define the level
     * and type of the node within the hierarchy.
     */
    interface Node : BaseNode {
        /**
         * Represents an instance of the `HDFramePlugin` associated with a particular node.
         * The property can hold a reference to an `HDFramePlugin` object or be null if not initialized.
         *
         * This variable is typically used to define the relationship between a node and its corresponding
         * `HDFramePlugin`, allowing the plugin to manage or interact with specific aspects of a node's behavior
         * or properties within the system.
         */
        var hDFramePlugin: HDFramePlugin?
    }

    /**
     * Adds a node to the list of child nodes and associates it with the current HDFramePlugin.
     *
     * @param node The node to be added. This node will have its `hDFramePlugin` property set to the current instance.
     */
    fun addNode(node: Node) {
        node.hDFramePlugin = this
        nodes.add(node)
    }
}
