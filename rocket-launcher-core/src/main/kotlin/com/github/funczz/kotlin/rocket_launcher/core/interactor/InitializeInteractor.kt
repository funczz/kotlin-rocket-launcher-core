package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.usecase.InitializeUseCase

object InitializeInteractor : InitializeUseCase {

    override fun invoke(data: RockerLauncher) {
        data.apply {
            initialCounter = RockerLauncher.DEFAULT_COUNTER
            currentCounter = RockerLauncher.DEFAULT_COUNTER
            isStarted = false
            isLaunched = false
            isAborted = false
            isTransitioned = true
        }
    }

}