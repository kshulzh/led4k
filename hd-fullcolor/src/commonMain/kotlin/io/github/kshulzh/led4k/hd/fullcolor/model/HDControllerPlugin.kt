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

/**
 * Represents a plugin for controlling HD devices, encapsulating details about its configuration,
 * communication properties, and associated nodes. This class extends the `BaseNode` sealed interface.
 *
 * @property appVersion The application version of the HD controller.
 * @property deviceModel The device model associated with the HD controller.
 * @property fileNotImport A configuration value specifying unsupported file imports.
 * @property height The height configuration for the display or device.
 * @property insertProject A flag indicating project insertion behavior.
 * @property newSpecialEffect The status of a special effect feature, e.g., "close".
 * @property rotation The rotation value for aligning or fixing display orientation.
 * @property stretch The stretch value representing how the content is stretched, if applicable.
 * @property svnVersion The version of the SVN associated with the plugin.
 * @property timeZone The timezone configuration for this plugin.
 * @property width The width configuration for the display or device.
 * @property zoomModulus A configuration value for zoom functionality.
 * @property __NAME__ The name of the controller plugin.
 * @property mimiScreen A setting for enabling or configuring miniature screen display, if applicable.
 * @property communicationList A list representing communication-specific items for the HD controller.
 * @property nodes A mutable list of child nodes associated with the HD controller plugin.
 */
data class HDControllerPlugin(
    var appVersion: String = "7.10.2.0",
    var deviceModel: String = "C16",
    var fileNotImport: Int = 0,
    var height: Int = 128,
    var insertProject: Int = 0,
    var newSpecialEffect: String = "close",
    var rotation: Int = 0,
    var stretch: Int = 0,
    var svnVersion: Int = 12673,
    var timeZone: Int = 0,
    var width: Int = 128,
    var zoomModulus: Int = 0,
    var __NAME__: String = "unnamed",
    var mimiScreen: Int = 0,
    var communicationList: CommunicationList =
        CommunicationList(mutableListOf(ListItem("${deviceModel}-24-058C8", "BoxPlayer"))),
    var nodes: MutableList<Node> = mutableListOf()
) : BaseNode {
    /**
     * The current hierarchical level of the node in the structure.
     * This property is used to represent the depth or position of the node within the hierarchy.
     * The default value is "1".
     */
    override var level: String = "1"
    /**
     * Represents the type of the HDControllerPlugin. This property is used to define
     * the specific type or category of the plugin, particularly identifying it as
     * an "HD_Controller_Plugin". It is an overridden property tailored to match the
     * requirements of the corresponding parent interface or class.
     */
    override var type: String = "HD_Controller_Plugin"

    init {
        nodes.forEach {
            it.hDControllerPlugin = this
        }
    }

    /**
     * Represents a specialized list container for managing communication-related items.
     * This class is a delegation of MutableList and contains additional metadata for the list.
     *
     * @property list The underlying list of ListItem objects that this class manages.
     * @property name A constant name identifier for this communication list.
     * @property index The default index position. It is set to -1 if the list is empty, or 0 otherwise.
     */
    class CommunicationList(val list: MutableList<ListItem> = mutableListOf()) : MutableList<ListItem> by list {
        /**
         * Represents the name associated with communication-related operations or entities.
         * Typically used as an identifier within the context of communication-related constructs.
         */
        val name = "communication"

        /**
         * Represents the index of the first element in the list if it is not empty,
         * or -1 if the list is empty.
         */
        val index = if (list.isEmpty()) -1 else 0
    }

    /**
     * Represents an individual item within a list.
     *
     * @property id A unique identifier for the list item.
     * @property name The name or label of the list item.
     */
    data class ListItem(
        var id: String,
        var name: String
    )

    /**
     * Represents a node in a hierarchy or structure that extends the functionality of `BaseNode`.
     * Nodes implementing this interface can optionally be associated with an instance of `HDControllerPlugin`.
     */
    interface Node : BaseNode {
        /**
         * Represents an optional instance of the HDControllerPlugin that can be associated with a node.
         *
         * This property serves as a reference to the `HDControllerPlugin` instance within a node, enabling
         * the node to access or interact with the plugin's functionality and attributes. When set, this
         * property links the node to the specified instance of `HDControllerPlugin`.
         *
         * If null, the node is not currently associated with any `HDControllerPlugin`.
         */
        var hDControllerPlugin: HDControllerPlugin?
    }

    /**
     * Adds a node to the list of nodes in the current HDControllerPlugin instance.
     * Sets the `hDControllerPlugin` property of the node to the current instance.
     *
     * @param node The node to be added to the `nodes` list.
     */
    fun addNode(node: Node) {
        node.hDControllerPlugin = this
        nodes.add(node)
    }
}
