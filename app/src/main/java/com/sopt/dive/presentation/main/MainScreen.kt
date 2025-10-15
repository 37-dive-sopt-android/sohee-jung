package com.sopt.dive.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R

@Composable
fun MainScreen(
    nickname: String,
    userId: String,
    password: String,
    mbti: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = "logo",
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = nickname,
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        Text(
            text = "안녕하세요. ${nickname}입니다.",
            color = Color.Black,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfo(
            userInfoSection = "ID",
            userInfoDescription = userId
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfo(
            userInfoSection = "PW",
            userInfoDescription = password
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfo(
            userInfoSection = "NICKNAME",
            userInfoDescription = nickname
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfo(
            userInfoSection = "MBTI",
            userInfoDescription = mbti
        )
    }
}

@Composable
private fun UserInfo(
    userInfoSection: String,
    userInfoDescription: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = userInfoSection,
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = userInfoDescription,
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}
