package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.TestFactory

class LaunchInteractorTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Pair(
            RocketLauncher(currentCounter = 0, state = Launched, isTransitioned = true),
            RocketLauncher(currentCounter = 0, state = Counting)
        ),
    ) { (expected, actual) ->
        interactor.invoke(actual)
        assertEquals(expected, actual)
    }

    @TestFactory
    fun failure() = casesDynamicTest(
        Pair(
            IllegalArgumentException::class.java,
            RocketLauncher(),
        ),
        Pair(
            IllegalArgumentException::class.java,
            RocketLauncher(currentCounter = 0, state = Launched, isTransitioned = true),
        ),
    ) { (expected, data) ->
        assertThrows(expected) {
            interactor.invoke(data)
        }
    }

    private val interactor = LaunchInteractor

}