package com.github.funczz.kotlin.rocket_launcher.core.state

import com.github.funczz.kotlin.fsm.NextState
import com.github.funczz.kotlin.rocket_launcher.core.event.*
import com.github.funczz.kotlin.rocket_launcher.core.interactor.InitializeInteractor
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.usecase.InitializeUseCase

object Ready : RocketLauncherState {

    private val initializeUseCase: InitializeUseCase = InitializeInteractor

    override fun getNextState(event: RocketLauncherEvent, context: RockerLauncher): NextState {
        return when (event) {
            is Initialize -> NextState.Deny
            is Start -> NextState.External(state = Counting)
            is Decrement -> NextState.Deny
            is Launch -> NextState.Deny
            is Abort -> NextState.Deny
        }
    }

    override fun onEntry(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        if (event !is Initialize) return context
        val result = context.copy()
        initializeUseCase.invoke(data = result)
        return result
    }

    override fun onDo(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        return context
    }

    override fun onExit(event: RocketLauncherEvent, context: RockerLauncher): RockerLauncher {
        return context
    }

}