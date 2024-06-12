package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.TestFactory

class LaunchInteractorTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Pair(
            RockerLauncher(currentCounter = 0, state = Launched, isTransitioned = true),
            RockerLauncher(currentCounter = 0, state = Counting)
        ),
    ) { (expected, actual) ->
        interactor.invoke(actual)
        assertEquals(expected, actual)
    }

    @TestFactory
    fun failure() = casesDynamicTest(
        Pair(
            IllegalArgumentException::class.java,
            RockerLauncher(),
        ),
        Pair(
            IllegalArgumentException::class.java,
            RockerLauncher(currentCounter = 0, state = Launched, isTransitioned = true),
        ),
    ) { (expected, data) ->
        assertThrows(expected) {
            interactor.invoke(data)
        }
    }

    private val interactor = LaunchInteractor

}