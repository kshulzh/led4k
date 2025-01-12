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

import com.github.kshulzh.led4k.common.effect.ContinuousEffect
import com.github.kshulzh.led4k.common.effect.Effect
import com.github.kshulzh.led4k.hd.fullcolor.program.toInt

interface HDEffectContinue : HDEffect {
    var headCloseToTail: Int
    override fun setEffect(e: Effect) {
        super.setEffect(e)
        if (e is ContinuousEffect) {
            dispTime = e.speed.toInt()
            dispEffect = e.continuousEffect.hdCode
            headCloseToTail = e.headCloseToTail.toInt()
            holdTime = 50
            clearEffect = 25
            clearTime = 5
        } else if (e is ContinuousEffect.ContinuousEffects) {
            dispEffect = e.hdCode
            holdTime = 50
            clearEffect = 25
            clearTime = 5
        }
    }
}