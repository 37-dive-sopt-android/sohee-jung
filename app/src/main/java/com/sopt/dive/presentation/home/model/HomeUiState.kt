package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class HomeUiState(
    val friends: PersistentList<HomeUiModel> = persistentListOf(),
)
