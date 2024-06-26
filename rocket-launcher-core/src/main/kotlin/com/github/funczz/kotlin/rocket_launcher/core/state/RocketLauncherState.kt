package com.github.funczz.kotlin.rocket_launcher.core.state

import com.github.funczz.kotlin.fsm.FsmState
import com.github.funczz.kotlin.rocket_launcher.core.event.RocketLauncherEvent
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher

sealed interface RocketLauncherState : FsmState<RocketLauncherEvent, RocketLauncher>