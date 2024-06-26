package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.TestFactory

class StartInteractorTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Pair(
            RocketLauncher(state = Counting, isTransitioned = true),
            RocketLauncher()
        ),
    ) { (expected, actual) ->
        interactor.invoke(actual)
        assertEquals(expected, actual)
    }

    @TestFactory
    fun failure() = casesDynamicTest(
        Pair(
            IllegalArgumentException::class.java,
            RocketLauncher(initialCounter = -1, currentCounter = -1),
        ),
        Pair(
            IllegalArgumentException::class.java,
            RocketLauncher(state = Counting),
        ),
    ) { (expected, data) ->
        assertThrows(expected) {
            interactor.invoke(data)
        }
    }

    private val interactor = StartInteractor

}