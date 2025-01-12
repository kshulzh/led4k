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

package com.github.kshulzh.led4k.common.effect

data class AnimatedEffect(val animatedEffects: AnimatedEffects, var speed: Double = 0.0) : Effect {
    enum class AnimatedEffects : Effect {
        RANDOM,
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
        COVER_LEFT,
        COVER_RIGHT,
        COVER_UP,
        COVER_DOWN,
        TOP_LEFT_COVER,
        BOTTOM_LEFT_COVERT,
        TOP_RIGHT_COVER,
        BOTTOM_RIGHT_COVERT,
        OPEN_FROM_MIDDLE,
        UP_DOWN_OPEN,
        CLOSE_FROM_MIDDLE,
        UP_DOWN_CLOSE,
        GRADUAL_CHANGE,
        VERTICAL_BLINDS,
        HORIZONTAL_BLINDS,
        TWINKLE
    }
}