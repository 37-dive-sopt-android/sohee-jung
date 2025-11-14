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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable
import com.sopt.dive.presentation.my.component.UserInfoContent

@Composable
fun MyRoute(
    paddingValues: PaddingValues,
    onNavigateToSignIn: () -> Unit,
) {
    val viewModel: MyViewModel = viewModel(
        factory = MyViewModelFactory()
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is MySideEffect.NavigateToSignIn -> onNavigateToSignIn()
            }
        }
    }

    MyScreen(
        uiState = uiState,
        paddingValues = paddingValues,
        onLogoutClick = viewModel::onLogoutClicked
    )
}

@Composable
private fun MyScreen(
    uiState: MyState,
    paddingValues: PaddingValues,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 20.dp, vertical = 40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = "logo",
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = uiState.username,
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        Text(
            text = "안녕하세요. ${uiState.name}입니다.",
            color = Color.Black,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfoContent(
            userInfoSection = "USERNAME",
            userInfoDescription = uiState.username
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfoContent(
            userInfoSection = "EMAIL",
            userInfoDescription = uiState.email
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfoContent(
            userInfoSection = "EMAIL",
            userInfoDescription = uiState.email
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserInfoContent(
            userInfoSection = "AGE",
            userInfoDescription = uiState.age.toString()
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "로그아웃",
            modifier = Modifier.noRippleClickable(onClick = onLogoutClick),
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic
        )
    }
}
