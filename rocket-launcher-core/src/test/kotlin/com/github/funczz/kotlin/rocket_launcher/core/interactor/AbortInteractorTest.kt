package com.github.funczz.kotlin.rocket_launcher.core.interactor

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestFactory

class AbortInteractorTest : Cases {

    @TestFactory
    fun success() = casesDynamicTest(
        Pair(
            RocketLauncher(state = Aborted, isTransitioned = true),
            RocketLauncher(state = Counting)
        ),
    ) { (expected, actual) ->
        interactor.invoke(actual)
        assertEquals(expected, actual)
    }

    private val interactor = AbortInteractor


}