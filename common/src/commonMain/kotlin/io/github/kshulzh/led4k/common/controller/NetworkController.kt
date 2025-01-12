/*
 * Copyright (c) 2024-2026. Kirill Shulzhenko
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

package io.github.kshulzh.led4k.common.controller

/**
 * Defines a network-based controller for managing and processing `Program` instances.
 * Extends the `Controller` interface by including a property for specifying the network address
 * of the target device or system. This enables additional functionalities that involve
 * communication over a network.
 *
 * Implementations of this interface are expected to coordinate the transmission or execution
 * of `Program` objects over a specific network, using the provided address as an endpoint
 * or reference for communication.
 */
interface NetworkController : Controller {
    /**
     * Specifies the address of the network controller.
     *
     * This property typically represents the network endpoint, such as an IP address
     * or hostname, that the `NetworkController` uses to communicate with its target system.
     * Implementations are expected to define how this address is utilized during
     * operations like program upload or status retrieval.
     */
    var address: String
}