package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import com.github.funczz.kotlin.rocket_launcher.core.usecase.AbortUseCase

object AbortInteractor : AbortUseCase {

    override fun invoke(data: RockerLauncher) {
        require(data = data)
        data.apply {
            state = Aborted
            isTransitioned = true
        }
    }

    private fun require(data: RockerLauncher) {
        val result = mutableListOf<String>()
        when (data.state) {
            is Ready, is Launched, is Aborted -> {
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