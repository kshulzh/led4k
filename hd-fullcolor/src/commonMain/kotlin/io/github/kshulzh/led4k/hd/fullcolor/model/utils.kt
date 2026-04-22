/*
 * Copyright (c) 2026. Kirill Shulzhenko
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

import io.github.kshulzh.led4k.common.model.Versions
import kotlinx.io.Sink
import kotlinx.io.writeString

/**
 * Writes the content of the given `HDControllerPlugin` instance to the sink.
 *
 * @param hdControllerPlugin The `HDControllerPlugin` instance whose content needs to be written.
 */
fun Sink.write(hdControllerPlugin: HDControllerPlugin) {
    hdControllerPlugin.write(0) {
        writeString(it)
    }
}

/**
 * Writes the content of the `HDControllerPlugin` instance in XML format.
 *
 * This method generates an XML representation of the `HDControllerPlugin` object,
 * including its attributes and associated nodes. The XML output is written through
 * the provided lambda function.
 *
 * @param level The current indentation level for XML output.
 * @param write1 A lambda function used to write the generated XML string.
 */
inline fun HDControllerPlugin.write(level: Int, write1: (String) -> Unit) {
    val level1 = level + 1
    write1("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")

    writeStart(level, write1)

    writeAttribute(level1, "AppVersion", appVersion, write1)
    writeAttribute(level1, "DeviceModel", deviceModel, write1)
    if (Versions.ofInts(appVersion) < Versions.ofInts("7.10.0.0")) {
        writeAttribute(level1, "FileNotImport", fileNotImport, write1)
    }
    writeAttribute(level1, "Height", height, write1)
    writeAttribute(level1, "InsertProject", insertProject, write1)
    writeAttribute(level1, "NewSpecialEffect", newSpecialEffect, write1)
    writeAttribute(level1, "Rotation", rotation, write1)
    writeAttribute(level1, "Stretch", stretch, write1)
    writeAttribute(level1, "SvnVersion", svnVersion, write1)
    writeAttribute(level1, "TimeZone", timeZone, write1)
    writeAttribute(level1, "Width", width, write1)
    writeAttribute(level1, "ZoomModulus", zoomModulus, write1)
    writeAttribute(level1, "__NAME__", __NAME__, write1)
    writeAttribute(level1, "mimiScreen", mimiScreen, write1)

    communicationList.write(level1, write1)

    nodes.forEach {
        it.write(level1, write1)
    }

    writeEnd(level, write1)
}

/**
 * Serializes the current `HDOrdinaryScenePlugin` instance into a structured format
 * by writing its attributes and nodes to the provided output function.
 *
 * @param level The indentation level used to format the output.
 * @param write1 A function to handle the serialized output strings.
 */
inline fun HDOrdinaryScenePlugin.write(level: Int, write1: (String) -> Unit) {
    val level1 = level + 1

    writeStart(level, write1)

    writeAttribute(level1, "Alpha", alpha, write1)
    writeAttribute(level1, "BgColor", bgColor, write1)
    writeAttribute(level1, "BgMode", bgMode, write1)
    writeAttribute(level1, "Checked", checked, write1)
    writeAttribute(level1, "FixedDuration", fixedDuration, write1)
    writeAttribute(level1, "FrameEffect", frameEffect, write1)
    writeAttribute(level1, "FrameSpeed", frameSpeed, write1)
    writeAttribute(level1, "FrameType", frameType, write1)
    writeAttribute(level1, "Friday", friday, write1)
    writeAttribute(level1, "Monday", monday, write1)
    writeAttribute(level1, "MotleyIndex", motleyIndex, write1)
    writeAttribute(level1, "PlayIndex", playIndex, write1)
    writeAttribute(level1, "PlayMode", playMode, write1)
    writeAttribute(level1, "PlayTimes", playTimes, write1)
    writeAttribute(level1, "PlayeTime", playeTime, write1)
    writeAttribute(level1, "PurityColor", purityColor, write1)
    writeAttribute(level1, "PurityIndex", purityIndex, write1)
    writeAttribute(level1, "Saturday", saturday, write1)
    writeAttribute(level1, "SpaceStartTime", spaceStartTime, write1)
    writeAttribute(level1, "SpaceStopTime", spaceStopTime, write1)
    writeAttribute(level1, "Sunday", sunday, write1)
    writeAttribute(level1, "Thursday", thursday, write1)
    writeAttribute(level1, "TricolorIndex", tricolorIndex, write1)
    writeAttribute(level1, "Tuesday", tuesday, write1)
    writeAttribute(level1, "UseSpacifiled", useSpacifiled, write1)
    writeAttribute(level1, "Volume", volume, write1)
    writeAttribute(level1, "Wednesday", wednesday, write1)
    writeAttribute(level1, "__GUID__", __GUID__, write1)
    writeAttribute(level1, "__NAME__", __NAME__, write1)

    nodes.forEach {
        it.write(level1, write1)
    }

    writeEnd(level, write1)
}

/**
 * Serializes the properties of the `HDFramePlugin` instance into a structured format using the provided write function.
 *
 * @param level The indentation level for the current node.
 * @param write1 A lambda function that writes a string output for serialization.
 */
inline fun HDFramePlugin.write(level: Int, write1: (String) -> Unit) {
    val level1 = level + 1

    writeStart(level, write1)

    writeAttribute(level1, "Alpha", alpha, write1)
    writeAttribute(level1, "Checked", checked, write1)
    writeAttribute(level1, "ChildType", childType, write1)
    writeAttribute(level1, "FrameSpeed", frameSpeed, write1)
    writeAttribute(level1, "FrameType", frameType, write1)
    writeAttribute(level1, "Height", height, write1)
    writeAttribute(level1, "Index", index, write1)
    writeAttribute(level1, "LockArea", lockArea, write1)
    writeAttribute(level1, "MotleyIndex", motleyIndex, write1)
    writeAttribute(level1, "PurityColor", purityColor, write1)
    writeAttribute(level1, "PurityIndex", purityIndex, write1)
    writeAttribute(level1, "TricolorIndex", tricolorIndex, write1)
    writeAttribute(level1, "Width", width, write1)
    writeAttribute(level1, "X", x, write1)
    writeAttribute(level1, "Y", y, write1)
    writeAttribute(level1, "__GUID__", __GUID__, write1)
    writeAttribute(level1, "__NAME__", __NAME__, write1)

    nodes.forEach {
        it.write(level1, write1)
    }

    writeEnd(level, write1)
}

/**
 * Writes the serialized representation of the current `HDVideoPlugin` instance, including its attributes,
 * nested file list, and additional metadata, into a specified output function.
 *
 * @param level The current level of indentation or depth in the output structure.
 * @param write1 A lambda function used to handle the output of serialized strings.
 */
inline fun HDVideoPlugin.write(level: Int, write1: (String) -> Unit) {
    val level1 = level + 1

    writeStart(level, write1)

    writeAttribute(level1, "AudioCodec", audioCodec, write1)
    writeAttribute(level1, "AudioCodecID", audioCodecID, write1)
    writeAttribute(level1, "AudioCommercial", audioCommercial, write1)
    writeAttribute(level1, "BitRate", bitRate, write1)
    writeAttribute(level1, "ByArea", byArea, write1)
    writeAttribute(level1, "ByTime", byTime, write1)
    writeAttribute(level1, "Checked", checked, write1)
    writeAttribute(level1, "CodecID", codecID, write1)
    writeAttribute(level1, "Encoding", encoding, write1)
    writeAttribute(level1, "FHeight", fHeight, write1)
    writeAttribute(level1, "FPS", FPS, write1)
    writeAttribute(level1, "FWidth", fWidth, write1)
    writeAttribute(level1, "FileMd5", fileMd5, write1)
    writeAttribute(level1, "HardwareAccele", hardwareAccele, write1)
    writeAttribute(level1, "PlayTimes", playTimes, write1)
    writeAttribute(level1, "Resolution", resolution, write1)
    writeAttribute(level1, "SourceFileTime", sourceFileTime, write1)
    writeAttribute(level1, "StreamFormat", streamFormat, write1)
    writeAttribute(level1, "UseRatio", useRatio, write1)
    writeAttribute(level1, "UseTranscoding", useTranscoding, write1)
    writeAttribute(level1, "VShotByArea", vShotByArea, write1)
    writeAttribute(level1, "VShotByTime", vShotByTime, write1)
    writeAttribute(level1, "VShotEnd", vShotEnd, write1)
    writeAttribute(level1, "VShotHeight", vShotHeight, write1)
    writeAttribute(level1, "VShotStart", vShotStart, write1)
    writeAttribute(level1, "VShotWidth", vShotWidth, write1)
    writeAttribute(level1, "VShotX", vShotX, write1)
    writeAttribute(level1, "VShotY", vShotY, write1)
    writeAttribute(level1, "VideoFormat", videoFormat, write1)
    writeAttribute(level1, "VideoProfile", videoProfile, write1)
    writeAttribute(level1, "VideoQuality", videoQuality, write1)
    writeAttribute(level1, "Volume", volume, write1)
    writeAttribute(level1, "__GUID__", __GUID__, write1)
    writeAttribute(level1, "__NAME__", __NAME__, write1)

    fileList.write(level1, write1)

    writeEnd(level, write1)
}

/**
 * Writes the serialized representation of the `HDPhotoPlugin` instance, including its attributes
 * and associated file list, into the provided output function.
 *
 * @param level The current indentation or depth level used for formatting the serialized output.
 * @param write1 A function that accepts a `String` and performs the write operation for each serialized line of text.
 */
inline fun HDPhotoPlugin.write(level: Int, write1: (String) -> Unit) {
    val level1 = level + 1

    writeStart(level, write1)

    writeAttribute(level1, "Checked", checked, write1)
    writeAttribute(level1, "ClearEffect", clearEffect, write1)
    writeAttribute(level1, "ClearTime", clearTime, write1)
    writeAttribute(level1, "ConvertImage", convertImage, write1)
    writeAttribute(level1, "DispEffect", dispEffect, write1)
    writeAttribute(level1, "DispTime", dispTime, write1)
    writeAttribute(level1, "HoldTime", holdTime, write1)
    writeAttribute(level1, "KeepRatio", keepRatio, write1)
    writeAttribute(level1, "PreloadFilePath", preloadFilePath, write1)
    writeAttribute(level1, "SpeedTimeIndex", speedTimeIndex, write1)
    writeAttribute(level1, "__GUID__", __GUID__, write1)
    writeAttribute(level1, "__NAME__", __NAME__, write1)

    fileList.write(level1, write1)

    writeEnd(level, write1)
}

/**
 * Serializes the `HDSingleLineTextPlugin` object to a structured textual representation.
 *
 * @param level The current indentation level for the serialized output.
 * @param write1 A function that defines how the serialized text is written or consumed.
 */
inline fun HDSingleLineTextPlugin.write(level: Int, write1: (String) -> Unit) {
    val level1 = level + 1
    writeStart(level, write1)

    writeAttribute(level1, "ByCount", byCount, write1)
    writeAttribute(level1, "ByTime", byTime, write1)
    writeAttribute(level1, "ClearEffect", clearEffect, write1)
    writeAttribute(level1, "ClearTime", clearTime, write1)
    writeAttribute(level1, "ContentAlign", contentAlign, write1)
    writeAttribute(level1, "ContentHAlign", contentHAlign, write1)
    writeAttribute(level1, "DispEffect", dispEffect, write1)
    writeAttribute(level1, "DispTime", dispTime, write1)
    writeAttribute(level1, "EditBgColor", editBgColor, write1)
    writeAttribute(level1, "HeadCloseToTail", headCloseToTail, write1)
    writeAttribute(level1, "HoldTime", holdTime, write1)
    writeAttribute(level1, "Html", html, write1)
    writeAttribute(level1, "PageCount", pageCount, write1)
    writeAttribute(level1, "PlayText", playText, write1)
    writeAttribute(level1, "PlayType", playType, write1)
    writeAttribute(level1, "SingleMode", singleMode, write1)
    writeAttribute(level1, "Speed", speed, write1)
    writeAttribute(level1, "SpeedTimeIndex", speedTimeIndex, write1)
    writeAttribute(level1, "StrokeColor", strokeColor, write1)
    writeAttribute(level1, "TextColor", textColor, write1)
    writeAttribute(level1, "TextFontName", textFontName, write1)
    writeAttribute(level1, "TextFontSize", textFontSize, write1)
    writeAttribute(level1, "TransBgColor", transBgColor, write1)
    writeAttribute(level1, "UseHollow", useHollow, write1)
    writeAttribute(level1, "UseStroke", useStroke, write1)
    writeAttribute(level1, "__GUID__", __GUID__, write1)
    writeAttribute(level1, "__NAME__", __NAME__, write1)

    fileList.write(level1, write1)

    writeEnd(level, write1)
}

/**
 * Serializes the instance of `HDTextPlugin` into a specific textual representation.
 * It writes out the attributes and properties of the instance in a formatted structure.
 *
 * @param level The indentation level for formatting the output.
 * @param write1 A lambda function that accepts a string and handles the actual writing
 *               or processing of the serialized output.
 */
inline fun HDTextPlugin.write(level: Int, write1: (String) -> Unit) {
    val level1 = level + 1
    writeStart(level, write1)

    writeAttribute(level1, "ClearEffect", clearEffect, write1)
    writeAttribute(level1, "ClearTime", clearTime, write1)
    writeAttribute(level1, "ContentAlign", contentAlign, write1)
    writeAttribute(level1, "DispEffect", dispEffect, write1)
    writeAttribute(level1, "DispTime", dispTime, write1)
    writeAttribute(level1, "EditBgColor", editBgColor, write1)
    writeAttribute(level1, "HeadCloseToTail", headCloseToTail, write1)
    writeAttribute(level1, "HoldTime", holdTime, write1)
    writeAttribute(level1, "Html", html, write1)
    writeAttribute(level1, "PageCount", pageCount, write1)
    writeAttribute(level1, "SingleMode", singleMode, write1)
    writeAttribute(level1, "SpeedTimeIndex", speedTimeIndex, write1)
    writeAttribute(level1, "StrokeColor", strokeColor, write1)
    writeAttribute(level1, "TransBgColor", transBgColor, write1)
    writeAttribute(level1, "UseHollow", useHollow, write1)
    writeAttribute(level1, "UseStroke", useStroke, write1)
    writeAttribute(level1, "__GUID__", __GUID__, write1)
    writeAttribute(level1, "__NAME__", __NAME__, write1)

    fileList.write(level1, write1)

    writeEnd(level, write1)
}

/**
 * Writes data of the current `Node` instance to a specified output function.
 * This operation is specifically implemented for `HDOrdinaryScenePlugin` nodes,
 * delegating the writing logic to their `write` method.
 *
 * @param level The current depth level in the hierarchy of nodes. Used for formatting or structuring the output.
 * @param write1 A lambda function to handle string output, consuming each line of the formatted content.
 */
inline fun HDControllerPlugin.Node.write(level: Int, write1: (String) -> Unit) {
    when (this) {
        is HDOrdinaryScenePlugin -> write(level, write1)
    }
}

/**
 * Writes the properties of this `Node` instance to the provided output function.
 *
 * @param level The current indentation level used for writing structured data.
 * @param write1 A function that accepts a `String` and processes the written output.
 */
inline fun HDOrdinaryScenePlugin.Node.write(level: Int, write1: (String) -> Unit) {
    when (this) {
        is HDFramePlugin -> write(level, write1)
    }
}

/**
 * Writes the details of the current node to the output using the provided write function.
 * The specific behavior of this method depends on the type of the node.
 *
 * @param level The current indentation level used for the output structure.
 * @param write1 A function used to write the serialized output for each attribute of the node.
 */
inline fun HDFramePlugin.Node.write(level: Int, write1: (String) -> Unit) {
    when (this) {
        is HDVideoPlugin -> write(level, write1)
        is HDPhotoPlugin -> write(level, write1)
        is HDSingleLineTextPlugin -> write(level, write1)
        is HDTextPlugin -> write(level, write1)
    }
}

/**
 * Writes the content of the `FileList` instance to the provided output using the specified write function.
 * Each item in the list is written in a structured format with appropriate XML elements.
 *
 * @param level The current indentation level to structure the output properly.
 * @param write1 A lambda function used for writing output strings.
 */
inline fun FileList.write(level: Int, write1: (String) -> Unit) {
    if (list.isEmpty()) {
        return
    }
    val level1 = level + 1

    writeIdents(level, write1)
    write1("<List Name=\"$name\" Index=\"${if (list.isEmpty()) "-1" else "0"}\">\n")

    list.forEach {
        writeIdents(level1, write1)
        write1("<ListItem MD5=\"${it.md5}\" FileName=\"${it.fileName}\" FileKey=\"${it.fileKey}\"/>\n")
    }

    writeIdents(level, write1)
    write1("</List>\n")
}

/**
 * Writes the XML representation of the communication list.
 *
 * @param level The indentation level to be used for the output.
 * @param write1 A lambda function to handle the line-by-line writing of the XML content.
 */
inline fun HDControllerPlugin.CommunicationList.write(level: Int, write1: (String) -> Unit) {
    if (list.isEmpty()) {
        return
    }
    val level1 = level + 1

    writeIdents(level, write1)
    write1("<List Name=\"$name\" Index=\"${if (list.isEmpty()) "-1" else "0"}\">\n")

    list.forEach {
        writeIdents(level1, write1)
        write1("<ListItem id=\"${it.id}\" name=\"${it.name}\"/>\n")
    }

    writeIdents(level, write1)
    write1("</List>\n")
}

/**
 * Writes the starting tag for a node in the hierarchical structure, including indentation
 * and opening the node definition.
 *
 * @param level The current indentation level, used to determine the amount of indentation.
 * @param write1 A lambda function to handle the writing of strings to an output or buffer.
 */
inline fun BaseNode.writeStart(level: Int, write1: (String) -> Unit) {
    writeIdents(level, write1)
    write1("<Node Level=\"${level + 1}\" Type=\"${type}\">\n")
}

/**
 * Writes the closing tag for a node in an XML-like structure, including proper indentation.
 *
 * @param level The current indentation level. Determines the number of indentation spaces.
 * @param write1 A lambda function used to write strings to the output.
 */
inline fun writeEnd(level: Int, write1: (String) -> Unit) {
    writeIdents(level, write1)
    write1("</Node>\n")
}

/**
 * Writes an XML attribute with an integer value by converting it to a string representation.
 *
 * @param level The indentation level for the attribute.
 * @param name The name of the attribute to be written.
 * @param value The integer value of the attribute, which will be converted to a string.
 * @param write1 A function that handles writing output strings.
 */
inline fun writeAttribute(level: Int, name: String, value: Int, write1: (String) -> Unit) {
    writeAttribute(level, name, value.toString(), write1)
}

/**
 * Writes an XML attribute representation with specified indentation level.
 *
 * @param level The indentation level to determine the number of spaces to prefix the attribute.
 * @param name The name of the attribute to be written.
 * @param value The value of the attribute to be written.
 * @param write1 A function that handles writing the output string.
 */
inline fun writeAttribute(level: Int, name: String, value: String, write1: (String) -> Unit) {
    writeIdents(level, write1)
    write1("<Attribute Name=\"$name\">$value</Attribute>\n")
}

/**
 * Writes indentation spaces based on the specified level.
 *
 * @param level The number of indentation levels to write. Each level corresponds to four spaces.
 * @param write1 A function that accepts a string and is called for each indentation level.
 */
inline fun writeIdents(level: Int, write1: (String) -> Unit) {
    repeat(level) { write1("    ") }
}

/**
 * Converts a Boolean value to its equivalent integer representation.
 *
 * @receiver The Boolean value to be converted.
 * @return 1 if the Boolean value is `true`, otherwise 0.
 */
fun Boolean.toInt() = if (this) 1 else 0

/**
 * Converts a [Long] value representing time duration in milliseconds to a formatted string in the
 * format "HH:mm:ss".
 *
 * This function computes the hours, minutes, and seconds from the given input in milliseconds
 * and returns the time in a zero-padded, human-readable format.
 *
 * @receiver A [Long] value representing the time duration in milliseconds.
 * @return A [String] representing the time in "HH:mm:ss" format.
 */
fun Long.toTime() = "${(this / 1000 / 60 / 60 % 24).toString().padStart(2, '0')}:${
    (this / 1000 / 60 % 60).toString().padStart(2, '0')
}:${(this / 1000 % 60).toString().padStart(2, '0')}"