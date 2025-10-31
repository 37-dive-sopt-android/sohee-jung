package com.sopt.dive.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchRoute(
    paddingValues: PaddingValues
){
    SearchScreen(
        paddingValues = paddingValues
    )
}

@Composable
private fun SearchScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        Text(
            text = "여기는 search!"
        )
    }
}
