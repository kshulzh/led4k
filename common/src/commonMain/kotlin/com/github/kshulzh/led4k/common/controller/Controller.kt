package com.github.kshulzh.led4k.common.controller

import com.github.kshulzh.led4k.common.model.areas.Area

interface Controller {
    fun send(area: Area)
}