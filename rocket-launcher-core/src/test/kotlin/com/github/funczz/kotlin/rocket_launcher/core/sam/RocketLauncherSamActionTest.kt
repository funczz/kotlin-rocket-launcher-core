package com.github.funczz.kotlin.rocket_launcher.core.sam

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.event.*
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.TestFactory

class RocketLauncherSamActionTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Triple(
            "Ready to Counting",
            Triple(true, false, false),
            InputData(initialCounter = 0, currentCounter = 0, state = Ready, event = Start(3)),
        ),
        Triple(
            "Counting to Launched (event=Launch)",
            Triple(true, true, false),
            InputData(initialCounter = 0, currentCounter = 0, state = Counting, event = Launch),
        ),
        Triple(
            "Counting to Counting",
            Triple(true, false, false),
            InputData(initialCounter = 10, currentCounter = 10, state = Counting, event = Decrement),
        ),
        Triple(
            "Counting to Launched (event=Decrement)",
            Triple(true, true, false),
            InputData(initialCounter = 1, currentCounter = 1, state = Counting, event = Decrement),
        ),
        Triple(
            "Counting to Aborted",
            Triple(true, false, true),
            InputData(initialCounter = 0, currentCounter = 0, state = Counting, event = Abort),
        ),
        Triple(
            "Launched to Ready",
            Triple(false, false, false),
            InputData(initialCounter = 0, currentCounter = 0, state = Launched, event = Initialize),
        ),
        Triple(
            "Aborted to Ready",
            Triple(false, false, false),
            InputData(initialCounter = 0, currentCounter = 0, state = Aborted, event = Initialize),
        ),
    ) { (_, expected, inputData) ->
        val samModel = RocketLauncherSamModel()
        RocketLauncherSamAction.accept(input = inputData, present = samModel::present)
        val actual = Triple(samModel.isStarted, samModel.isLaunched, samModel.isAborted)
        assertEquals(expected, actual)
    }

    @TestFactory
    fun `failure IllegalArgumentException`() = casesDynamicTest(
        Pair(Ready, Initialize),
        Pair(Ready, Decrement),
        Pair(Ready, Launch),
        Pair(Ready, Abort),
        Pair(Counting, Initialize),
        Pair(Launched, Decrement),
        Pair(Launched, Launch),
        Pair(Launched, Abort),
        Pair(Aborted, Decrement),
        Pair(Aborted, Launch),
        Pair(Aborted, Abort),
    ) { (state, event) ->
        val samModel = RocketLauncherSamModel()
        val inputData = InputData(initialCounter = 0, currentCounter = 0, state = state, event = event)
        assertThrows(IllegalArgumentException::class.java) {
            RocketLauncherSamAction.accept(input = inputData, present = samModel::present)
        }
    }

}