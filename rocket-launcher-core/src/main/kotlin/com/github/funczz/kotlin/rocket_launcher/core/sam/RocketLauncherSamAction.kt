package com.github.funczz.kotlin.rocket_launcher.core.sam

import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.sam.SamAction

object RocketLauncherSamAction : SamAction<InputData, RockerLauncher> {

    override fun accept(input: InputData, present: (RockerLauncher) -> Unit) {
        val state = input.state
        val event = input.event
        val rockerLauncher = RockerLauncher(
            initialCounter = input.initialCounter,
            currentCounter = input.currentCounter,
            state = input.state,
        )

        val (_, new) = state.fire(event = event, context = rockerLauncher)
        present(new)
    }

}