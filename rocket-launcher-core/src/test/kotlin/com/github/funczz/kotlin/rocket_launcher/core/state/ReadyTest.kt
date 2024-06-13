package com.github.funczz.kotlin.rocket_launcher.core.state

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.event.*
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.TestFactory

class ReadyTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Triple(
            Pair(
                Counting,
                RocketLauncher(initialCounter = 3, currentCounter = 3, state = Counting, isTransitioned = true)
            ),
            Start(3),
            RocketLauncher()
        ),
    ) { (expected, event, context) ->
        val actual = state.fire(event = event, context = context)
        assertEquals(expected, actual)
    }

    @TestFactory
    fun `failure IllegalArgumentException`() = casesDynamicTest(
        //Pair(Start(10), RockerLauncher()),
        Pair(Initialize, RocketLauncher()),
        Pair(Decrement, RocketLauncher()),
        Pair(Launch, RocketLauncher()),
        Pair(Abort, RocketLauncher()),
    ) { (event, context) ->
        assertThrows(IllegalArgumentException::class.java) {
            state.fire(event = event, context = context)
        }
    }

    private val state = Ready

}