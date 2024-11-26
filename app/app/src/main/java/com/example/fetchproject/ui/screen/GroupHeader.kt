package com.example.fetchproject.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fetchproject.R
import com.example.fetchproject.ui.theme.Purple80
import com.example.fetchproject.ui.viewstate.HeaderViewState

@Composable
fun GroupHeader(
    viewState: HeaderViewState
) {
    Text(
        text = stringResource(R.string.list_id_header, viewState.title),
        fontSize = 24.sp,
        fontWeight = FontWeight.W200,
        modifier = Modifier.fillMaxWidth().background(Purple80).padding(8.dp)
    )
}