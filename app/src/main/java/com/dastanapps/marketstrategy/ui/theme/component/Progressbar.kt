package com.dastanapps.marketstrategy.ui.theme.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 *
 * Created by Iqbal Ahmed on 22/01/2024
 *
 */

@Composable
fun CircularProgress(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}