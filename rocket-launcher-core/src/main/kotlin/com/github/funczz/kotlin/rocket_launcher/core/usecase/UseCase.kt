package com.github.funczz.kotlin.rocket_launcher.core.usecase

interface UseCase<DATA> {

    fun invoke(data: DATA)

}