package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import com.github.funczz.kotlin.rocket_launcher.core.usecase.LaunchUseCase

object LaunchInteractor : LaunchUseCase {

    override fun invoke(data: RocketLauncher) {
        require(data = data)
        data.apply {
            state = Launched
            isTransitioned = true
        }
    }

    private fun require(data: RocketLauncher) {
        val result = mutableListOf<String>()
        if (data.initialCounter < 0) result.add("initialCounter < 0")
        if (data.currentCounter != 0) result.add("currentCounter != 0")
        if (data.state !is Counting) result.add("state is ${data.state::class.simpleName}")
        if (result.isNotEmpty()) {
            val message = "%s(%s)".format("RockerLauncher", result.joinToString(", "))
            throw IllegalArgumentException(message)
        }
    }

}