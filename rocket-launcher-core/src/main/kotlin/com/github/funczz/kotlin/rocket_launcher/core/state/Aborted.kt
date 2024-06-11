package com.github.funczz.kotlin.rocket_launcher.core.state


import com.github.funczz.kotlin.fsm.NextState
import com.github.funczz.kotlin.rocket_launcher.core.event.*
import com.github.funczz.kotlin.rocket_launcher.core.interactor.AbortInteractor
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.usecase.AbortUseCase

object Aborted : RocketLauncherState {

    private val abortUseCase: AbortUseCase = AbortInteractor

    override fun getNextState(event: RocketLauncherEvent, context: RockerLauncher): NextState {
        return when (event) {
            is Initialize -> NextState.External(state = Ready)
            is Start -> NextState.Deny
            is Decrement -> NextState.Deny
            is Launch -> NextState.Deny
            is Abort -> NextState.Deny
        }
    }

    override fun onEntry(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        if (event !is Abort) return context
        val result = context.copy()
        abortUseCase.invoke(data = result)
        return result
    }

    override fun onDo(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        return context
    }

    override fun onExit(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        return context
    }

}