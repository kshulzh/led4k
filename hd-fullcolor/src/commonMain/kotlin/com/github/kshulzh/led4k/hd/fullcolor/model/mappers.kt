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

import com.github.kshulzh.led4k.common.model.Versions

fun BaseNode.asNode(): Node {
    return when (this) {
        is HDControllerPlugin -> this.asNode()
        is HDOrdinaryScenePlugin -> this.asNode()
        is HDFramePlugin -> this.asNode()
        is HDPhotoPlugin -> this.asNode()
        is HDVideoPlugin -> this.asNode()
        is HDSingleLineTextPlugin -> this.asNode()
        else -> throw RuntimeException("Unsupported")
    }
}

fun HDControllerPlugin.CommunicationList.asList(): List {
    return List(
        name,
        index.toString(),
        list.map { it.asListItem() }.toMutableList()
    )
}

fun FileList.asList(): List {
    return List(
        name,
        if (list.isEmpty()) "-1" else "0",
        list.map { it.asListItem() }.toMutableList()
    )
}

fun FileItem.asListItem(): ListItem {
    return ListItem(
        MD5 = md5,
        fileName = fileName,
        fileKey = fileKey
    )
}

fun HDControllerPlugin.ListItem.asListItem(): ListItem {
    return ListItem(
        id = id,
        name = name
    )
}

fun HDControllerPlugin.asNode(): Node {
    return Node(
        level,
        type,
        mutableListOf(
            Attribute("AppVersion", appVersion),
            Attribute("DeviceModel", deviceModel),
            Attribute("FileNotImport", fileNotImport),
            Attribute("Height", height),
            Attribute("InsertProject", insertProject),
            Attribute("NewSpecialEffect", newSpecialEffect),
            Attribute("Rotation", rotation),
            Attribute("Stretch", stretch),
            Attribute("SvnVersion", svnVersion),
            Attribute("TimeZone", timeZone),
            Attribute("Width", width),
            Attribute("ZoomModulus", zoomModulus),
            Attribute("__NAME__", __NAME__),
            Attribute("mimiScreen", mimiScreen)
        ).also {
            if (Versions.ofInts(appVersion) >= Versions.ofInts("7.10.0.0")) {
                it.remove(Attribute("FileNotImport", fileNotImport))
            }
        },
        communicationList.asList(),
        nodes.map { it.asNode() }.toMutableList()
    )
}

fun HDOrdinaryScenePlugin.asNode(): Node {
    return Node(
        level,
        type,
        mutableListOf(
            Attribute("Alpha", alpha),
            Attribute("BgColor", bgColor),
            Attribute("BgMode", bgMode),
            Attribute("Checked", checked),
            Attribute("FixedDuration", fixedDuration),
            Attribute("FrameEffect", frameEffect),
            Attribute("FrameSpeed", frameSpeed),
            Attribute("FrameType", frameType),
            Attribute("Friday", friday),
            Attribute("Monday", monday),
            Attribute("MotleyIndex", motleyIndex),
            Attribute("PlayIndex", playIndex),
            Attribute("PlayMode", playMode),
            Attribute("PlayTimes", playTimes),
            Attribute("PlayeTime", playeTime),
            Attribute("PurityColor", purityColor),
            Attribute("PurityIndex", purityIndex),
            Attribute("Saturday", saturday),
            Attribute("SpaceStartTime", spaceStartTime),
            Attribute("SpaceStopTime", spaceStopTime),
            Attribute("Sunday", sunday),
            Attribute("Thursday", thursday),
            Attribute("TricolorIndex", tricolorIndex),
            Attribute("Tuesday", tuesday),
            Attribute("UseSpacifiled", useSpacifiled),
            Attribute("Volume", volume),
            Attribute("Wednesday", wednesday),
            Attribute("__GUID__", __GUID__),
            Attribute("__NAME__", __NAME__),
        ),
        fileList.asList(),
        nodes.map { (it as BaseNode).asNode() }.toMutableList()
    )
}

fun HDFramePlugin.asNode(): Node {
    return Node(
        level,
        type,
        mutableListOf(
            Attribute("Alpha", alpha),
            Attribute("Checked", checked),
            Attribute("ChildType", childType),
            Attribute("FrameSpeed", frameSpeed),
            Attribute("FrameType", frameType),
            Attribute("Height", height),
            Attribute("Index", index),
            Attribute("LockArea", lockArea),
            Attribute("MotleyIndex", motleyIndex),
            Attribute("PurityColor", purityColor),
            Attribute("PurityIndex", purityIndex),
            Attribute("TricolorIndex", tricolorIndex),
            Attribute("Width", width),
            Attribute("X", x),
            Attribute("Y", y),
            Attribute("__GUID__", __GUID__),
            Attribute("__NAME__", __NAME__)
        ).also {
            val hdControllerPlugin = this.hDOrdinaryScenePlugin?.hDControllerPlugin
            if (hdControllerPlugin != null && Versions.ofInts(hdControllerPlugin.appVersion) >= Versions.ofInts("7.10.0.0")) {
                it.remove(Attribute("Checked", checked))
            }
        },
        null,
        nodes.map { (it as BaseNode).asNode() }.toMutableList()
    )
}

fun HDPhotoPlugin.asNode(): Node {
    return Node(
        level,
        type,
        mutableListOf(
            Attribute("Checked", checked),
            Attribute("ClearEffect", clearEffect),
            Attribute("ClearTime", clearTime),
            Attribute("ConvertImage", convertImage),
            Attribute("DispEffect", dispEffect),
            Attribute("DispTime", dispTime),
            Attribute("HoldTime", holdTime),
            Attribute("KeepRatio", keepRatio),
            Attribute("PreloadFilePath", preloadFilePath),
            Attribute("SpeedTimeIndex", speedTimeIndex),
            Attribute("__GUID__", __GUID__),
            Attribute("__NAME__", __NAME__),
        ).also {
            val hdControllerPlugin = this.hDFramePlugin?.hDOrdinaryScenePlugin?.hDControllerPlugin
            if (hdControllerPlugin != null && Versions.ofInts(hdControllerPlugin.appVersion) >= Versions.ofInts("7.10.0.0")) {
                it.remove(Attribute("Checked", checked))
            }
        },
        fileList.asList(),
        mutableListOf()
    )
}

fun HDVideoPlugin.asNode(): Node {
    return Node(
        level,
        type,
        mutableListOf(
            Attribute("AudioCodec", audioCodec),
            Attribute("AudioCodecID", audioCodecID),
            Attribute("AudioCommercial", audioCommercial),
            Attribute("BitRate", bitRate),
            Attribute("ByArea", byArea),
            Attribute("ByTime", byTime),
            Attribute("Checked", checked),
            Attribute("CodecID", codecID),
            Attribute("Encoding", encoding),
            Attribute("FHeight", fHeight),
            Attribute("FPS", FPS),
            Attribute("FWidth", fWidth),
            Attribute("FileMd5", fileMd5),
            Attribute("HardwareAccele", hardwareAccele),
            Attribute("PlayTimes", playTimes),
            Attribute("Resolution", resolution),
            Attribute("SourceFileTime", sourceFileTime),
            Attribute("StreamFormat", streamFormat),
            Attribute("UseRatio", useRatio),
            Attribute("UseTranscoding", useTranscoding),
            Attribute("VShotByArea", vShotByArea),
            Attribute("VShotByTime", vShotByTime),
            Attribute("VShotEnd", vShotEnd),
            Attribute("VShotHeight", vShotHeight),
            Attribute("VShotStart", vShotStart),
            Attribute("VShotWidth", vShotWidth),
            Attribute("VShotX", vShotX),
            Attribute("VShotY", vShotY),
            Attribute("VideoFormat", videoFormat),
            Attribute("VideoProfile", videoProfile),
            Attribute("VideoQuality", videoQuality),
            Attribute("Volume", volume),
            Attribute("__GUID__", __GUID__),
            Attribute("__NAME__", __NAME__),
        ),
        fileList.asList(),
        mutableListOf()
    )
}

fun HDSingleLineTextPlugin.asNode(): Node {
    return Node(
        level,
        type,
        mutableListOf(
            Attribute("ByCount", byCount),
            Attribute("ByTime", byTime),
            Attribute("ClearEffect", clearEffect),
            Attribute("ClearTime", clearTime),
            Attribute("ContentAlign", contentAlign),
            Attribute("ContentHAlign", contentHAlign),
            Attribute("DispEffect", dispEffect),
            Attribute("DispTime", dispTime),
            Attribute("EditBgColor", editBgColor),
            Attribute("HeadCloseToTail", headCloseToTail),
            Attribute("HoldTime", holdTime),
            Attribute("Html", html),
            Attribute("PageCount", pageCount),
            Attribute("PlayText", playText),
            Attribute("PlayType", playType),
            Attribute("SingleMode", singleMode),
            Attribute("Speed", speed),
            Attribute("SpeedTimeIndex", speedTimeIndex),
            Attribute("StrokeColor", strokeColor),
            Attribute("TextColor", textColor),
            Attribute("TextFontName", textFontName),
            Attribute("TextFontSize", textFontName),
            Attribute("TransBgColor", transBgColor),
            Attribute("UseHollow", useHollow),
            Attribute("UseStroke", useStroke),
            Attribute("__GUID__", __GUID__),
            Attribute("__NAME__", __NAME__),
        ),
        fileList.asList(),
        mutableListOf()
    )
}


