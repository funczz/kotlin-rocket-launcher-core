package com.github.funczz.kotlin.rocket_launcher.core.sam

import com.github.funczz.kotlin.sam.SamStateRepresentation

object RocketLauncherSamStateRepresentation : SamStateRepresentation<RocketLauncherSamModel, RocketLauncherSamModel> {

    override fun representation(model: RocketLauncherSamModel): RocketLauncherSamModel {
        return model
    }

}