package com.github.kshulzh.led4k.common.exception

class AreaTypeNotFoundException(val areaName: String) : NotFoundException() {
    override val message: String = "Area with name '$areaName' not found"
}