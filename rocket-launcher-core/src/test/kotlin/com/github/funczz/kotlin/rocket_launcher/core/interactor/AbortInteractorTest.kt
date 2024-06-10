package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestFactory

class AbortInteractorTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Pair(
            RockerLauncher(isAborted = true, isTransitioned = true),
            RockerLauncher()
        ),
        Pair(
            RockerLauncher(isStarted = true, isAborted = true, isTransitioned = true),
            RockerLauncher(isStarted = true)
        ),
    ) { (expected, actual) ->
        interactor.invoke(actual)
        assertEquals(expected, actual)
    }

    private val interactor = AbortInteractor


}