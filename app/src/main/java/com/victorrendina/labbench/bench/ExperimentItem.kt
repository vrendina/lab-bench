package com.victorrendina.labbench.bench

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ExperimentItem(
    val id: Experiment,
    @DrawableRes val iconRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int
)
