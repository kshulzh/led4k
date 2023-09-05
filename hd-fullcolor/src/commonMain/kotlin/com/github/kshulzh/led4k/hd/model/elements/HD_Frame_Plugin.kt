/*
 *   Copyright (c) 2023. Kyrylo Shulzhenko
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.github.kshulzh.led4k.hd.model.elements

class HD_Frame_Plugin : HD_Node() {
    override var name: String = "HD_Frame_Plugin"
    lateinit var alpha: String
    lateinit var checked: String
    lateinit var childType: String
    lateinit var frameSpeed: String
    lateinit var frameType: String
    lateinit var height: String
    lateinit var index: String
    lateinit var lockArea: String
    lateinit var motleyIndex: String
    lateinit var purityColor: String
    lateinit var purityIndex: String
    lateinit var tricolorIndex: String
    lateinit var width: String
    lateinit var x: String
    lateinit var y: String
    lateinit var __GUID__: String
    lateinit var __NAME__: String
}