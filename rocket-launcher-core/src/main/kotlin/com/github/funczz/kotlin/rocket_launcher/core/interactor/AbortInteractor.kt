package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.usecase.AbortUseCase

object AbortInteractor : AbortUseCase {

    override fun invoke(data: RockerLauncher) {
        data.apply {
            isAborted = true
            isTransitioned = true
        }
    }

}