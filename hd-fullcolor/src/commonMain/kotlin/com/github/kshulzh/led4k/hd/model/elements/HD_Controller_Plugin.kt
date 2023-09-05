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

class HD_Controller_Plugin : HD_Node() {
    override var name: String = "HD_Controller_Plugin"
    lateinit var appVersion: String
    lateinit var deviceModel: String
    lateinit var height: String
    lateinit var insertProject: String
    lateinit var rotation: String
    lateinit var stretch: String
    lateinit var svnVersion: String
    lateinit var timeZone: String
    lateinit var width: String
    lateinit var zoomModulus: String
    lateinit var __NAME__: String

    inner class Comunication {
        lateinit var id: String
        lateinit var name: String
    }
}