package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.usecase.DecrementUseCase

object DecrementInteractor : DecrementUseCase {

    override fun invoke(data: RockerLauncher) {
        require(data = data)
        data.apply {
            currentCounter -= 1
            isTransitioned = false
        }
    }

    private fun require(data: RockerLauncher) {
        val result = mutableListOf<String>()
        if (data.initialCounter < 0) result.add("initialCounter < 0")
        if (data.currentCounter <= 0) result.add("currentCounter <= 0")
        if (!data.isStarted) result.add("isStarted is false")
        if (data.isLaunched) result.add("isLaunched is true")
        if (data.isAborted) result.add("isLaunched is true")
        if (result.isNotEmpty()) {
            val message = "%s(%s)".format("RockerLauncher", result.joinToString(", "))
            throw IllegalArgumentException(message)
        }
    }
}