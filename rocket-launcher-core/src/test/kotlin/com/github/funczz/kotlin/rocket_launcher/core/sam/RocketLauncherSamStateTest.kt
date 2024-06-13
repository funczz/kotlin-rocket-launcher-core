package com.github.funczz.kotlin.rocket_launcher.core.sam

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestFactory

class RocketLauncherSamStateTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Triple(
            "RockerLauncher is Initial data",
            "Ready.",
            RocketLauncher(),
        ),
        Triple(
            "RockerLauncher: Counting(10)",
            "Counting. currentCounter=10",
            RocketLauncher(state = Counting),
        ),
        Triple(
            "RockerLauncher: Counting(0)",
            "Launched.",
            RocketLauncher(currentCounter = 0, state = Counting),
        ),
        Triple(
            "RockerLauncher: Launched",
            "Launched.",
            RocketLauncher(currentCounter = 0, state = Launched),
        ),
        Triple(
            "RockerLauncher: Aborted",
            "Aborted.",
            RocketLauncher(currentCounter = 10, state = Aborted),
        ),
    ) { (_, expected, data) ->
        val samModel = RocketLauncherSamModel().apply { present(data = data) }
        RocketLauncherSamStateRepresentation.representation(model = samModel, ::render)
        assertEquals(expected, actual)
    }

    private fun render(model: RocketLauncherSamModel) {
        actual = when {
            RocketLauncherSamState.isReady(model = model) -> {
                "Ready."
            }

            RocketLauncherSamState.isCounting(model = model) -> {
                "Counting. currentCounter=${model.currentCounter}"
            }

            RocketLauncherSamState.isWaiting(model = model) -> {
                "Waiting. currentCounter=${model.currentCounter}"
            }

            RocketLauncherSamState.isLaunched(model = model) -> {
                "Launched."
            }

            RocketLauncherSamState.isAborted(model = model) -> {
                "Aborted."
            }

            else -> {
                "error!"
            }
        }
    }

    private lateinit var actual: String

    override fun setUpCases() {
        actual = ""
    }

}