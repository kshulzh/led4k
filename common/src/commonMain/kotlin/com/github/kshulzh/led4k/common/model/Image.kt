package com.github.kshulzh.led4k.common.model

data class Image(val width: Int, val height: Int, var pixels: IntArray) {
    constructor(width: Int, height: Int) : this(width, height, IntArray(width * height))
}