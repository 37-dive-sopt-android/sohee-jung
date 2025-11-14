package com.sopt.dive.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.presentation.home.component.UserFriendsItem
import com.sopt.dive.presentation.home.model.HomeUiState
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        paddingValues = paddingValues,
        userFriends = uiState.value
    )
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    userFriends: ImmutableList<HomeUiState>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {
        items(items = userFriends, key = { it.friendName }) { friend ->
            UserFriendsItem(friend)
        }
    }
}
