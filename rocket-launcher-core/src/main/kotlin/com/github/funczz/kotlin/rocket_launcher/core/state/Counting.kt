package com.github.funczz.kotlin.rocket_launcher.core.state

import com.github.funczz.kotlin.fsm.NextState
import com.github.funczz.kotlin.rocket_launcher.core.event.*
import com.github.funczz.kotlin.rocket_launcher.core.interactor.DecrementInteractor
import com.github.funczz.kotlin.rocket_launcher.core.interactor.StartInteractor
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.usecase.DecrementUseCase
import com.github.funczz.kotlin.rocket_launcher.core.usecase.StartUseCase

object Counting : RocketLauncherState {

    private val startUseCase: StartUseCase = StartInteractor

    private val decrementUseCase: DecrementUseCase = DecrementInteractor

    override fun getNextState(event: RocketLauncherEvent, context: RockerLauncher): NextState {
        return when (event) {
            is Initialize -> NextState.Deny
            is Start -> NextState.Deny
            is Decrement -> NextState.Internal
            is Launch -> NextState.External(state = Launched)
            is Abort -> NextState.External(state = Aborted)
        }
    }

    override fun onEntry(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        if (event !is Start) return context
        val result = context.copy()
        result.initialCounter = event.initialCounter
        result.currentCounter = event.initialCounter
        startUseCase.invoke(data = result)
        return result
    }

    override fun onDo(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        if (event !is Decrement) return context
        val result = context.copy()
        decrementUseCase.invoke(data = result)
        return result
    }

    override fun onExit(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        return context
    }

}