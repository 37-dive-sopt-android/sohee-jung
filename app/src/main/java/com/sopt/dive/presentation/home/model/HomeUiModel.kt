package com.sopt.dive.presentation.home.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class HomeUiModel(
    @DrawableRes val friendImage: Int,
    val friendName: String,
    val friendSaying: String? = null,
    val birthday: Birthday = Birthday.NONE,
    val subContent: SubContent
){
    val saying: Saying
        get() = if (friendSaying == null) Saying.NONE else Saying.SAYING
}
