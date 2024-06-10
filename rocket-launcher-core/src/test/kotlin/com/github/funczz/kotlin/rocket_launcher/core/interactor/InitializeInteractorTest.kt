package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestFactory

class InitializeInteractorTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        RockerLauncher(initialCounter = 3, currentCounter = 3),
        RockerLauncher(isStarted = true, isLaunched = true, isAborted = true, isTransitioned = true),
    ) { actual ->
        interactor.invoke(actual)
        assertEquals(expected, actual)
    }

    private val interactor = InitializeInteractor

    private val expected = RockerLauncher(isTransitioned = true)

}