package com.sopt.dive.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.data.UserFriendsInfo
import com.sopt.dive.presentation.home.component.UserFriendsItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeRoute(
    paddingValues: PaddingValues
) {
    val userFriendsDummy = listOf<UserFriendsInfo>(
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "가나다",
            friendSaying = "안녕"
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "나다라",
            friendSaying = "안녕"
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "라마바",
            friendSaying = "안녕"
        ), UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "사아자",
            friendSaying = "안녕"
        ), UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "ㄱㄴㄷ",
            friendSaying = "안녕"
        ), UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "ㄹㅁㅂ",
            friendSaying = "안녕"
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "abc",
            friendSaying = "안녕"
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "def",
            friendSaying = "안녕"
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "ghi",
            friendSaying = "안녕"
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "jkl",
            friendSaying = "안녕"
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "mno",
            friendSaying = "안녕"
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "pqr",
            friendSaying = "안녕"
        )
    )

    HomeScreen(
        paddingValues = paddingValues,
        userFriends = userFriendsDummy.toImmutableList()
    )
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    userFriends: ImmutableList<UserFriendsInfo>,
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
            UserFriendsItem(userFriends = friend)
        }
    }
}
