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
@SerialName("List")
class FileList(val list: MutableList<FileItem> = mutableListOf()) : MutableList<FileItem> by list {
    @SerialName("Name")
    val name = "__FileList__"

    @SerialName("Index")
    val index = if (list.isEmpty()) -1 else 0
}

@Serializable
data class FileItem(
    var md5: String,
    var fileName: String,
    var fileKey: String
)

interface FileListNode {
    var fileList: FileList
}