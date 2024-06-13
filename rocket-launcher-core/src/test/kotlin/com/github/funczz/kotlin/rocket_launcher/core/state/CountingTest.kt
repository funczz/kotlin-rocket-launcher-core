package com.github.funczz.kotlin.rocket_launcher.core.state

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.event.*
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.TestFactory

class CountingTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Triple(
            Pair(Counting, RocketLauncher(currentCounter = 9, state = Counting, isTransitioned = false)),
            Decrement,
            RocketLauncher(state = Counting)
        ),
        Triple(
            Pair(
                Launched,
                RocketLauncher(currentCounter = 0, state = Launched, isTransitioned = true)
            ),
            Launch,
            RocketLauncher(currentCounter = 0, state = Counting)
        ),
        Triple(
            Pair(
                Aborted,
                RocketLauncher(currentCounter = 7, state = Aborted, isTransitioned = true)
            ),
            Abort,
            RocketLauncher(currentCounter = 7, state = Counting)
        ),
    ) { (expected, event, context) ->
        val actual = state.fire(event = event, context = context)
        assertEquals(expected, actual)
    }

    @TestFactory
    fun `failure IllegalArgumentException`() = casesDynamicTest(
        Pair(Initialize, RocketLauncher()),
        Pair(Start(10), RocketLauncher()),
        //Pair(Decrement, RockerLauncher()),
        //Pair(Launch, RockerLauncher()),
        //Pair(Abort, RockerLauncher()),
    ) { (event, context) ->
        assertThrows(IllegalArgumentException::class.java) {
            state.fire(event = event, context = context)
        }
    }

    private val state = Counting

}