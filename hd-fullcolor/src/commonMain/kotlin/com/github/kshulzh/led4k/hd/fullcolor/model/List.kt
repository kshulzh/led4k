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

@Serializable
data class List(
    @SerialName("Name")
    var name: String,
    @SerialName("Index")
    var index: String,
    val list: MutableList<ListItem>
)

@Serializable
data class ListItem(
    var id: String? = null,
    var name: String? = null,
    var MD5: String? = null,
    @SerialName("FileKey")
    var fileKey: String? = null,
    @SerialName("FileName")
    var fileName: String? = null,
)
