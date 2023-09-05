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

class HD_Text_Plugin : HD_Node() {
    override var name: String = "HD_Text_Plugin"
    lateinit var checked: String
    lateinit var clearEffect: String
    lateinit var clearTime: String
    lateinit var colorfulTextEnable: String
    lateinit var colorfulTextIndex: String
    lateinit var colorfulTextSelect: String
    lateinit var colorfulTextSpeed: String
    lateinit var contentAlign: String
    lateinit var dispEffect: String
    lateinit var dispTime: String
    lateinit var editBgColor: String
    lateinit var headCloseToTail: String
    lateinit var holdTime: String
    lateinit var html: String
    lateinit var pageCount: String
    lateinit var singleMode: String
    lateinit var speedTimeIndex: String
    lateinit var strokeColor: String
    lateinit var transBgColor: String
    lateinit var useHollow: String
    lateinit var useStroke: String
    lateinit var __GUID__: String
    lateinit var __NAME__: String
    lateinit var __FileList__: List<FileElement>
}