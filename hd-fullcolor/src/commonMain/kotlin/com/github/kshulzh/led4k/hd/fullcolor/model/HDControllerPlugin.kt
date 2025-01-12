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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    override var level: String = "1"
    override var type: String = "HD_Controller_Plugin"

    init {
        nodes.forEach {
            it.hDControllerPlugin = this
        }
    }

    @Serializable
    @SerialName("List")
    class CommunicationList(val list: MutableList<ListItem> = mutableListOf()) : MutableList<ListItem> by list {
        @SerialName("Name")
        val name = "communication"

        @SerialName("Index")
        val index = if (list.isEmpty()) -1 else 0
    }

    @Serializable
    data class ListItem(
        var id: String,
        var name: String
    )

    interface Node : BaseNode {
        var hDControllerPlugin: HDControllerPlugin?
    }

    fun addNode(node: Node) {
        node.hDControllerPlugin = this
        nodes.add(node)
    }
}
