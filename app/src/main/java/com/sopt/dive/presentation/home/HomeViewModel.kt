package com.sopt.dive.presentation.home

import androidx.lifecycle.ViewModel
import com.sopt.dive.R
import com.sopt.dive.presentation.home.model.Birthday
import com.sopt.dive.presentation.home.model.HomeUiModel
import com.sopt.dive.presentation.home.model.HomeUiState
import com.sopt.dive.presentation.home.model.SubContent
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()


    init {
        _uiState.update { friends ->
            friends.copy(friends = getDummyData())
        }
    }

    private fun getDummyData() = persistentListOf(
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "가나다",
            friendSaying = "안녕",
            subContent = SubContent.None
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "나다라",
            friendSaying = "안녕",
            birthday = Birthday.BIRTHDAY,
            subContent = SubContent.Birthday
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "라마바",
            subContent = SubContent.Song(songName = "너에게 간다", singer = "윤종신")
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "사아자",
            friendSaying = "안녕",
            subContent = SubContent.Song(songName = "너에게 간다", singer = "윤종신")
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "ㄱㄴㄷ",
            birthday = Birthday.BIRTHDAY,
            subContent = SubContent.Birthday
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "ㄹㅁㅂ",
            friendSaying = "안녕",
            subContent = SubContent.None
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "ㅂㅅㅇ",
            friendSaying = "안녕",
            subContent = SubContent.None
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "기니디",
            friendSaying = "안녕",
            birthday = Birthday.BIRTHDAY,
            subContent = SubContent.Birthday
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "리미비",
            subContent = SubContent.Song(songName = "너에게 간다", singer = "윤종신")
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "지치키",
            friendSaying = "안녕",
            subContent = SubContent.Song(songName = "너에게 간다", singer = "윤종신")
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "ㅋㅋㅋ",
            birthday = Birthday.BIRTHDAY,
            subContent = SubContent.Birthday
        ),
        HomeUiModel(
            friendImage = R.drawable.img_profile,
            friendName = "ㅎㅎㅎ",
            friendSaying = "안녕",
            subContent = SubContent.None
        )
    )
}
