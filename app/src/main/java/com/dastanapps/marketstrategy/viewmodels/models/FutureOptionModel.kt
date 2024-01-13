package com.dastanapps.marketstrategy.viewmodels.models

import androidx.compose.runtime.MutableState

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */

data class SelectedValueItem(
    val value: MutableState<String>,
    val onValueChange: ((String) -> Unit)? = null
)

data class SelectedValue(
    val symbol: SelectedValueItem,
    var strikePrice: SelectedValueItem,
    var expiryDate: SelectedValueItem
) {
    fun canShowQuote(): Boolean {
        return symbol.value.value.isNotEmpty() &&
                strikePrice.value.value.isNotEmpty() &&
                expiryDate.value.value.isNotEmpty()
    }
}