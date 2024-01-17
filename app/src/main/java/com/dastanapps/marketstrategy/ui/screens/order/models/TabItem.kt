package com.dastanapps.marketstrategy.ui.screens.order.models

import androidx.compose.runtime.Composable

/**
 *
 * Created by Iqbal Ahmed on 16/01/2024
 *
 */

data class TabItem(
    val title: String,
    val content: @Composable ()->Unit
)
