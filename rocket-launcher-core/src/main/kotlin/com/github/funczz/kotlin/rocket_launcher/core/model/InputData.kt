package com.github.funczz.kotlin.rocket_launcher.core.model

import com.github.funczz.kotlin.rocket_launcher.core.event.RocketLauncherEvent
import com.github.funczz.kotlin.rocket_launcher.core.state.RocketLauncherState

data class InputData(

    val initialCounter: Int,

    val currentCounter: Int,

    val state: RocketLauncherState,

    val event: RocketLauncherEvent,

    )