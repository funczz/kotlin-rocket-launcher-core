package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RockerLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestFactory

class InitializeInteractorTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        RockerLauncher(state = Launched, isTransitioned = true),
        RockerLauncher(state = Aborted, isTransitioned = true),
    ) { actual ->
        interactor.invoke(actual)
        assertEquals(expected, actual)
    }

    private val interactor = InitializeInteractor

    private val expected = RockerLauncher(isTransitioned = true)

}