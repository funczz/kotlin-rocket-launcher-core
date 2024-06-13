package com.github.funczz.kotlin.rocket_launcher.core.sam

import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.sam.SamAction

object RocketLauncherSamAction : SamAction<InputData, RocketLauncher> {

    override fun accept(input: InputData, present: (RocketLauncher) -> Unit) {
        val state = input.state
        val event = input.event
        val rocketLauncher = RocketLauncher(
            initialCounter = input.initialCounter,
            currentCounter = input.currentCounter,
            state = input.state,
        )

        val (_, new) = state.fire(event = event, context = rocketLauncher)
        present(new)
    }

}