package com.github.funczz.kotlin.rocket_launcher.core.state

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.event.*
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.TestFactory

class AbortedTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Triple(
            Pair(Ready, RockerLauncher(isTransitioned = true)),
            Initialize,
            RockerLauncher(isStarted = true)
        ),
    ) { (expected, event, context) ->
        val actual = state.fire(event = event, context = context)
        assertEquals(expected, actual)
    }

    @TestFactory
    fun `failure IllegalArgumentException`() = casesDynamicTest(
        //Pair(Initialize, RockerLauncher()),
        Pair(Start(10), RockerLauncher()),
        Pair(Decrement, RockerLauncher()),
        Pair(Launch, RockerLauncher()),
        Pair(Abort, RockerLauncher()),
    ) { (event, context) ->
        assertThrows(IllegalArgumentException::class.java) {
            state.fire(event = event, context = context)
        }
    }

    private val state = Aborted

}