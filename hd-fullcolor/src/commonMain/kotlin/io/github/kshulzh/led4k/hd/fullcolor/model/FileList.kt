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
 * A class that represents a list of FileItem objects. This class delegates its functionality
 * to a MutableList of FileItem objects, enabling it to behave like a mutable list while
 * maintaining additional properties for specific use cases.
 *
 * @property list The underlying mutable list that stores FileItem objects. Defaults to an empty list.
 * @property name The name identifier of this list, set to a constant value "__FileList__".
 * @property index The starting index of the list. It is set to -1 if the list is empty,
 * or 0 if the list contains elements.
 */
class FileList(val list: MutableList<FileItem> = mutableListOf()) : MutableList<FileItem> by list {
    /**
     * A constant name identifier for the `FileList` class, typically used
     * as a label or descriptor for the object.
     */
    val name = "__FileList__"
    /**
     * Represents the index of the first item in the list.
     * If the list is empty, the value is set to -1.
     * Otherwise, the value is set to 0, pointing to the first item in the list.
     */
    val index = if (list.isEmpty()) -1 else 0
}

/**
 * Represents a file with its associated metadata.
 *
 * @property md5 The MD5 checksum of the file used for verifying integrity.
 * @property fileName The name of the file.
 * @property fileKey A unique key or identifier associated with the file.
 */
data class FileItem(
    var md5: String,
    var fileName: String,
    var fileKey: String
)

/**
 * Represents a node interface containing a reference to a `FileList` instance.
 * Classes implementing this interface can manage or interact with a `FileList`.
 */
interface FileListNode {
    /**
     * Represents the file list associated with this node.
     * This variable provides access to the underlying collection of file items,
     * allowing modification and traversal of the list as needed.
     */
    var fileList: FileList
}