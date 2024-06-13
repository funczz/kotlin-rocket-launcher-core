package com.github.funczz.kotlin.rocket_launcher.core.sam

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestFactory

class RocketLauncherSamModelTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Triple(
            "RocketLauncher is Initial data",
            RocketLauncherData(
                initialCounter = 10,
                currentCounter = 10,
                isStarted = false,
                isLaunched = false,
                isAborted = false,
                isTransitioned = false
            ),
            RocketLauncher(),
        ),
        Triple(
            "RocketLauncher: Counting state",
            RocketLauncherData(
                initialCounter = 10,
                currentCounter = 10,
                isStarted = true,
                isLaunched = false,
                isAborted = false,
                isTransitioned = false
            ),
            RocketLauncher(state = Counting),
        ),
        Triple(
            "RocketLauncher: Launched state",
            RocketLauncherData(
                initialCounter = 0,
                currentCounter = 0,
                isStarted = true,
                isLaunched = true,
                isAborted = false,
                isTransitioned = false
            ),
            RocketLauncher(initialCounter = 0, currentCounter = 0, state = Launched),
        ),
        Triple(
            "RocketLauncher: Aborted state",
            RocketLauncherData(
                initialCounter = 10,
                currentCounter = 10,
                isStarted = true,
                isLaunched = false,
                isAborted = true,
                isTransitioned = false
            ),
            RocketLauncher(state = Aborted),
        ),
    ) { (_, expected, data) ->
        val samModel = RocketLauncherSamModel().apply {
            present(data = data)
        }
        val actual = samModel.toData()
        assertEquals(expected, actual)
    }

    data class RocketLauncherData(
        val initialCounter: Int,
        val currentCounter: Int,
        val isStarted: Boolean,
        val isLaunched: Boolean,
        val isAborted: Boolean,
        val isTransitioned: Boolean,
    )

    @Suppress("Unused")
    private fun RocketLauncherSamModel.toData(): RocketLauncherData = RocketLauncherData(
        initialCounter = this.initialCounter,
        currentCounter = this.currentCounter,
        isStarted = this.isStarted,
        isLaunched = this.isLaunched,
        isAborted = this.isAborted,
        isTransitioned = this.isTransitioned
    )

}