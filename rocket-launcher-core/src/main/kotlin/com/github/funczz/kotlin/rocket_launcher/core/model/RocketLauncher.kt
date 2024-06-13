package com.github.funczz.kotlin.rocket_launcher.core.model

import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import com.github.funczz.kotlin.rocket_launcher.core.state.RocketLauncherState

data class RocketLauncher(

    var initialCounter: Int = DEFAULT_COUNTER,

    var currentCounter: Int = initialCounter,

    var state: RocketLauncherState = Ready,

    var isTransitioned: Boolean = false,

    ) {

    companion object {

        const val DEFAULT_COUNTER = 10

    }
}