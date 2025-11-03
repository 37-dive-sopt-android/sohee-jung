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
import com.sopt.dive.presentation.home.component.UserFriendsItem
import com.sopt.dive.presentation.home.model.Birthday
import com.sopt.dive.presentation.home.model.SubContent
import com.sopt.dive.presentation.home.model.UserFriendsInfo
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
            friendSaying = "안녕",
            subContent = SubContent.None
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "나다라",
            friendSaying = "안녕",
            birthday = Birthday.BIRTHDAY,
            subContent = SubContent.Birthday
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "라마바",
            subContent = SubContent.Song(songName = "너에게 간다", singer = "윤종신")
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "사아자",
            friendSaying = "안녕",
            subContent = SubContent.Song(songName = "너에게 간다", singer = "윤종신")
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "ㄱㄴㄷ",
            birthday = Birthday.BIRTHDAY,
            subContent = SubContent.Birthday
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "ㄹㅁㅂ",
            friendSaying = "안녕",
            subContent = SubContent.None
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "ㅂㅅㅇ",
            friendSaying = "안녕",
            subContent = SubContent.None
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "기니디",
            friendSaying = "안녕",
            birthday = Birthday.BIRTHDAY,
            subContent = SubContent.Birthday
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "리미비",
            subContent = SubContent.Song(songName = "너에게 간다", singer = "윤종신")
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "지치키",
            friendSaying = "안녕",
            subContent = SubContent.Song(songName = "너에게 간다", singer = "윤종신")
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "ㅋㅋㅋ",
            birthday = Birthday.BIRTHDAY,
            subContent = SubContent.Birthday
        ),
        UserFriendsInfo(
            friendImage = R.drawable.img_profile,
            friendName = "ㅎㅎㅎ",
            friendSaying = "안녕",
            subContent = SubContent.None
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
