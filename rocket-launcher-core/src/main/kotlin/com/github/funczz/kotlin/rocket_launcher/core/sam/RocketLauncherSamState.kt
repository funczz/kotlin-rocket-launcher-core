package com.github.funczz.kotlin.rocket_launcher.core.sam

import com.github.funczz.kotlin.rocket_launcher.core.event.Launch
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.sam.SamStateNextAction

object RocketLauncherSamState : SamStateNextAction<RocketLauncherSamModel> {

    override fun nextAction(model: RocketLauncherSamModel): Boolean {
        /** Counting状態かつカウントが 0 なら Launchイベントを適用する */
        if (isWaiting(model = model)) {
            val inputData = InputData(
                initialCounter = model.initialCounter,
                currentCounter = model.currentCounter,
                state = Counting,
                event = Launch,
            )
            RocketLauncherSamAction.accept(input = inputData, model::present)
            return true
        }
        return false
    }

    /**
     * @param model モデル
     * @return 待機状態なら真
     */
    @Suppress("Unused")
    fun isReady(model: RocketLauncherSamModel): Boolean =
        model.currentCounter > 0 && !model.isStarted && !model.isAborted && !model.isLaunched

    /**
     * @param model モデル
     * @return Counting状態なら真
     */
    @Suppress("Unused")
    fun isCounting(model: RocketLauncherSamModel): Boolean =
        model.currentCounter > 0 && model.isStarted && !model.isAborted && !model.isLaunched

    /**
     * @param model モデル
     * @return Counting状態かつカウントが 0 なら真
     */
    @Suppress("Unused")
    fun isWaiting(model: RocketLauncherSamModel): Boolean =
        model.currentCounter == 0 && model.isStarted && !model.isAborted && !model.isLaunched

    /**
     * @param model モデル
     * @return Launched状態なら真
     */
    @Suppress("Unused")
    fun isLaunched(model: RocketLauncherSamModel): Boolean =
        model.currentCounter == 0 && model.isStarted && !model.isAborted && model.isLaunched

    /**
     * @param model モデル
     * @return Aborted状態了なら真
     */
    @Suppress("Unused")
    fun isAborted(model: RocketLauncherSamModel): Boolean =
        model.isStarted && model.isAborted && !model.isLaunched

}