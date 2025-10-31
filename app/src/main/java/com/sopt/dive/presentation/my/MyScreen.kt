package com.sopt.dive.presentation.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable
import com.sopt.dive.data.UserInfo
import com.sopt.dive.data.UserPrefs
import com.sopt.dive.presentation.my.component.UserInfoContent
import kotlinx.coroutines.launch

@Composable
fun MyRoute(
    paddingValues: PaddingValues,
    onNavigateToSignIn: () -> Unit
){
    val context = LocalContext.current
    val prefs = remember { UserPrefs(context) }
    val profile by prefs.profileFlow.collectAsStateWithLifecycle(initialValue = UserInfo.EMPTY)
    val scope = rememberCoroutineScope()

    MyScreen(
        nickname = profile.nickname,
        userId = profile.userId,
        password = profile.password,
        mbti = profile.mbti,
        paddingValues = paddingValues,
        onLogoutClick = {
            scope.launch {
                prefs.clear()
                onNavigateToSignIn()
            }
        }
    )
}

@Composable
private fun MyScreen(
    nickname: String,
    userId: String,
    password: String,
    mbti: String,
    paddingValues: PaddingValues,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
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

        UserInfoContent(
            userInfoSection = "ID",
            userInfoDescription = userId
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfoContent(
            userInfoSection = "PW",
            userInfoDescription = password
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfoContent(
            userInfoSection = "NICKNAME",
            userInfoDescription = nickname
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfoContent(
            userInfoSection = "MBTI",
            userInfoDescription = mbti
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "로그아웃",
            modifier = Modifier.noRippleClickable(
                onClick = onLogoutClick
            ),
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic
        )
    }
}
