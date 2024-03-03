package com.example.a30daysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val nameRes: Int,
    @StringRes val instructionRes: Int,
    @DrawableRes val imageRes: Int,
)