/*
 * Copyright (c) 2025. Kirill Shulzhenko
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

package com.github.kshulzh.led4k.hd.fullcolor.effect

import com.github.kshulzh.led4k.common.effect.AnimatedEffect
import com.github.kshulzh.led4k.common.effect.BasicEffects
import com.github.kshulzh.led4k.common.effect.ContinuousEffect


val ContinuousEffect.ContinuousEffects.hdCode
    get() = when (this) {
        ContinuousEffect.ContinuousEffects.CONTINUOUS_MOVE_UP -> 28
        ContinuousEffect.ContinuousEffects.CONTINUOUS_MOVE_LEFT -> 26
        ContinuousEffect.ContinuousEffects.CONTINUOUS_MOVE_RIGHT -> 27
        ContinuousEffect.ContinuousEffects.CONTINUOUS_MOVE_DOWN -> TODO()
    }

val BasicEffects.hdCode
    get() = when (this) {
        BasicEffects.DEFAULT -> TODO()
        BasicEffects.IMMEDIATE_SHOW -> 0
        BasicEffects.DONT_CLEAR_SCREEN -> 20
        BasicEffects.IMMEDIATE_CLEAR -> 0
    }

val AnimatedEffect.AnimatedEffects.hdCode
    get() = when (this) {
        AnimatedEffect.AnimatedEffects.MOVE_LEFT -> 1
        AnimatedEffect.AnimatedEffects.MOVE_RIGHT -> 2
        AnimatedEffect.AnimatedEffects.MOVE_UP -> 3
        AnimatedEffect.AnimatedEffects.MOVE_DOWN -> 4
        AnimatedEffect.AnimatedEffects.COVER_LEFT -> 5
        AnimatedEffect.AnimatedEffects.COVER_RIGHT -> 6
        AnimatedEffect.AnimatedEffects.COVER_UP -> 7
        AnimatedEffect.AnimatedEffects.COVER_DOWN -> 8
        AnimatedEffect.AnimatedEffects.TOP_LEFT_COVER -> 9
        AnimatedEffect.AnimatedEffects.BOTTOM_LEFT_COVERT -> 10
        AnimatedEffect.AnimatedEffects.TOP_RIGHT_COVER -> 11
        AnimatedEffect.AnimatedEffects.BOTTOM_RIGHT_COVERT -> 12
        AnimatedEffect.AnimatedEffects.OPEN_FROM_MIDDLE -> 13
        AnimatedEffect.AnimatedEffects.UP_DOWN_OPEN -> 14
        AnimatedEffect.AnimatedEffects.CLOSE_FROM_MIDDLE -> 15
        AnimatedEffect.AnimatedEffects.UP_DOWN_CLOSE -> 16
        AnimatedEffect.AnimatedEffects.GRADUAL_CHANGE -> 17
        AnimatedEffect.AnimatedEffects.VERTICAL_BLINDS -> 18
        AnimatedEffect.AnimatedEffects.HORIZONTAL_BLINDS -> 19
        AnimatedEffect.AnimatedEffects.TWINKLE -> 30
        AnimatedEffect.AnimatedEffects.RANDOM -> 25
    }