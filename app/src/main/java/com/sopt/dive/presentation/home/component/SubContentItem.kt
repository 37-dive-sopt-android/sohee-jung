package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sopt.dive.presentation.home.model.SubContent

@Composable
fun SubContentItem(
    subContent: SubContent,
    modifier: Modifier = Modifier
) {
    when (subContent) {
        is SubContent.Song -> SongChip(
            songName = subContent.songName,
            singer = subContent.singer,
            modifier = modifier
        )

        is SubContent.Birthday -> BirthdayChip(modifier = modifier)
        is SubContent.None -> {}
    }
}

@Composable
private fun SongChip(
    songName: String,
    singer: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$songName - $singer",
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color.Green, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 3.dp)
    )
}

@Composable
private fun BirthdayChip(
    modifier: Modifier = Modifier
) {
    Text(
        text = "선물하기 🎁",
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 3.dp)
    )
}
