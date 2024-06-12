package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import com.github.funczz.kotlin.rocket_launcher.core.usecase.InitializeUseCase

object InitializeInteractor : InitializeUseCase {

    override fun invoke(data: RockerLauncher) {
        require(data = data)
        data.apply {
            initialCounter = RockerLauncher.DEFAULT_COUNTER
            currentCounter = RockerLauncher.DEFAULT_COUNTER
            state = Ready
            isTransitioned = true
        }
    }

    private fun require(data: RockerLauncher) {
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