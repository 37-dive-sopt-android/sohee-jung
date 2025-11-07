package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.presentation.home.model.Birthday
import com.sopt.dive.presentation.home.model.HomeUiState
import com.sopt.dive.presentation.home.model.Saying

@Composable
fun UserFriendsItem(
    userFriends: HomeUiState,
    modifier: Modifier = Modifier
) {
    val birthdayIcon = remember(userFriends.birthday) {
        when (userFriends.birthday) {
            Birthday.BIRTHDAY -> "🎉"
            Birthday.NONE -> null
        }
    }

    val friendsSaying = remember(userFriends.saying) {
        when (userFriends.saying) {
            Saying.SAYING -> userFriends.friendSaying
            Saying.NONE -> null
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = userFriends.friendImage),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(shape = RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(5.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = userFriends.friendName,
                color = Color.Black,
                fontSize = 15.sp
            )

            friendsSaying?.let { userFriends ->
                Text(
                    text = userFriends,
                    fontSize = 10.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(2.dp))

        birthdayIcon?.let { birthdayIcon ->
            Text(text = birthdayIcon)
        }

        Spacer(modifier = Modifier.weight(1f))

        SubContentItem(
            subContent = userFriends.subContent
        )
    }
}
