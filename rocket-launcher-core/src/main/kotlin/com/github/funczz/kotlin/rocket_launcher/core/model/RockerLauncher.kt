package com.github.funczz.kotlin.rocket_launcher.core.model

data class RockerLauncher(

    var initialCounter: Int = DEFAULT_COUNTER,

    var currentCounter: Int = initialCounter,

    var isStarted: Boolean = false,

    var isLaunched: Boolean = false,

    var isAborted: Boolean = false,

    var isTransitioned: Boolean = false,

    ) {

    companion object {

        const val DEFAULT_COUNTER = 10

    }
}