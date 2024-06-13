package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import com.github.funczz.kotlin.rocket_launcher.core.usecase.InitializeUseCase

object InitializeInteractor : InitializeUseCase {

    override fun invoke(data: RocketLauncher) {
        require(data = data)
        data.apply {
            initialCounter = RocketLauncher.DEFAULT_COUNTER
            currentCounter = RocketLauncher.DEFAULT_COUNTER
            state = Ready
            isTransitioned = true
        }
    }

    private fun require(data: RocketLauncher) {
        val result = mutableListOf<String>()
        when (data.state) {
            is Ready, is Counting -> {
                result.add("state is ${data.state::class.simpleName}")
            }

            else -> {}
        }
        if (result.isNotEmpty()) {
            val message = "%s(%s)".format("RockerLauncher", result.joinToString(", "))
            throw IllegalArgumentException(message)
        }
    }

}