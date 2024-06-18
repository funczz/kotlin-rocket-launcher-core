package com.github.funczz.kotlin.rocket_launcher.core.sam

import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import com.github.funczz.kotlin.sam.SamModel
import java.util.*


class RocketLauncherSamModel : SamModel<RocketLauncher> {

    /** 初期カウント値 */
    @Suppress("MemberVisibilityCanBePrivate")
    var initialCounter: Int = 0
        private set

    /** カウント値 */
    @Suppress("MemberVisibilityCanBePrivate")
    var currentCounter: Int = 0
        private set

    /** Counting状態なら真、それ以外は偽 */
    @Suppress("MemberVisibilityCanBePrivate")
    var isStarted: Boolean = false
        private set

    /** Launched状態なら真、それ以外は偽 */
    @Suppress("MemberVisibilityCanBePrivate")
    var isLaunched: Boolean = false
        private set

    /** Aborted状態なら真、それ以外は偽 */
    @Suppress("MemberVisibilityCanBePrivate")
    var isAborted: Boolean = false
        private set

    /** ビューを更新するなら真、それ以外は偽 */
    @Suppress("MemberVisibilityCanBePrivate")
    var isTransitioned: Boolean = false
        private set

    override fun present(data: RocketLauncher) {
        /** イベント値をモデルに適用 */
        initialCounter = data.initialCounter
        currentCounter = data.currentCounter
        when (data.state) {
            is Ready -> {
                isStarted = false
                isLaunched = false
                isAborted = false
            }

            is Counting -> {
                isStarted = true
                isLaunched = false
                isAborted = false
            }

            is Launched -> {
                isStarted = true
                isLaunched = true
                isAborted = false
            }

            is Aborted -> {
                isStarted = true
                isLaunched = false
                isAborted = true
            }
        }
        isTransitioned = data.isTransitioned

        /** 次アクションを適用 */
        while (true) {
            if (!RocketLauncherSamState.nextAction(model = this)) break
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as RocketLauncherSamModel
        return initialCounter == other.initialCounter && currentCounter == other.currentCounter && isStarted == other.isStarted && isLaunched == other.isLaunched && isAborted == other.isAborted && isTransitioned == other.isTransitioned
    }

    override fun hashCode(): Int {
        var result = 17
        result = 31 * result + Objects.hashCode(initialCounter)
        result = 31 * result + Objects.hashCode(currentCounter)
        result = 31 * result + Objects.hashCode(isStarted)
        result = 31 * result + Objects.hashCode(isLaunched)
        result = 31 * result + Objects.hashCode(isAborted)
        result = 31 * result + Objects.hashCode(isTransitioned)
        return result
    }

    override fun toString(): String {
        return "%s(initialCounter=%d,currentCounter=%d,isStarted=%b,isLaunched=%b,isAborted=%b,isTransitioned=%b)".format(
            this::class.simpleName,
            initialCounter,
            currentCounter,
            isStarted,
            isLaunched,
            isAborted,
            isTransitioned,
        )
    }

}