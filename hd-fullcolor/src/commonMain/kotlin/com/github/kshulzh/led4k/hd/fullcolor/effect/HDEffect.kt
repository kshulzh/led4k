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
import com.github.kshulzh.led4k.common.effect.DisplayCloseEffect
import com.github.kshulzh.led4k.common.effect.Effect

interface HDEffect {
    var clearEffect: Int
    var clearTime: Int
    var dispEffect: Int
    var holdTime: Int
    var dispTime: Int

    fun setEffect(e: Effect) {
        when (e) {
            is DisplayCloseEffect -> e
            is AnimatedEffect -> DisplayCloseEffect(e)
            is BasicEffects -> DisplayCloseEffect(e)
            is AnimatedEffect.AnimatedEffects -> DisplayCloseEffect(e)
            else -> return
        }.also {
            holdTime = it.hold.toInt() * 10
            when (val displayEffect = it.displayEffect) {
                is BasicEffects -> {
                    if (displayEffect != BasicEffects.DEFAULT) {
                        dispEffect = displayEffect.hdCode
                    }
                }

                is AnimatedEffect -> {
                    dispTime = displayEffect.speed.toInt()
                    dispEffect = displayEffect.animatedEffects.hdCode
                }

                is AnimatedEffect.AnimatedEffects -> {
                    dispEffect = displayEffect.hdCode
                }
            }
            when (val clearEffect = it.clearEffect) {
                is BasicEffects -> {
                    if (clearEffect != BasicEffects.DEFAULT) {
                        this.clearEffect = clearEffect.hdCode
                    }
                }

                is AnimatedEffect -> {
                    clearTime = clearEffect.speed.toInt()
                    this.clearEffect = clearEffect.animatedEffects.hdCode
                }

                is AnimatedEffect.AnimatedEffects -> {
                    this.clearEffect = clearEffect.hdCode
                }
            }
        }

    }
}