package com.sopt.dive.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.util.noRippleClickable
import com.sopt.dive.presentation.main.MainNavTab
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MainBottomBar(
    visible: Boolean,
    tabs: ImmutableList<MainNavTab>,
    currentTab: MainNavTab?,
    onTabClicked: (MainNavTab) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.DarkGray)
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(vertical = 10.dp)
            ) {
                tabs.forEach { tab ->
                    key(tab.route) {
                        val selected = currentTab == tab
                        MainBottomBarItem(
                            selected = selected,
                            onBottomBarIconClick = { onTabClicked(tab) },
                            tab = tab
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.MainBottomBarItem(
    selected: Boolean,
    onBottomBarIconClick: () -> Unit,
    tab: MainNavTab,
    modifier: Modifier = Modifier
) {
    val bottomBarColor = if (selected) Color.White else Color.Gray

    Column(
        modifier = modifier
            .noRippleClickable(onClick = onBottomBarIconClick)
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = tab.icon),
            contentDescription = tab.contentDescription,
            tint = bottomBarColor
        )

        Text(
            text = tab.contentDescription,
            color = bottomBarColor
        )
    }
}
