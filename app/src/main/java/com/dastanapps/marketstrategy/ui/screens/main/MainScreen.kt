package com.dastanapps.marketstrategy.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dastanapps.marketstrategy.data.models.FutureOptionIndicesData
import com.dastanapps.marketstrategy.ui.theme.component.Dropdown
import com.dastanapps.marketstrategy.ui.theme.component.SearchBox
import com.dastanapps.marketstrategy.ui.theme.component.SearchBoxState
import com.dastanapps.marketstrategy.viewmodels.models.SelectedValue

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

data class FutureOptionState(
    val searchBoxState: SearchBoxState,
    val optionList: MutableState<List<String>>,
    val selectedItem: SelectedValue,
    val displayData: MutableState<FutureOptionIndicesData> = mutableStateOf(FutureOptionIndicesData.empty()),
    val getQuotesClick:()->Unit
)

@Composable
fun FutureOptionScreen(
    state: FutureOptionState,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = "Underlying Value: ${state.displayData.value.underlyingValue}",
            modifier = Modifier.padding(16.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            val selectedValue = state.selectedItem
            Dropdown(
                items = state.optionList.value,
                selectedItem = selectedValue.symbol.value.value.ifEmpty { "Symbol" },
                modifier = Modifier.weight(1f)
            ) {
                selectedValue.symbol.value.value = it
                selectedValue.symbol.onValueChange?.invoke(it)
            }

            Dropdown(
                items = state.displayData.value.filterBy.strikePrice.map { it.toString() },
                selectedItem = selectedValue.strikePrice.value.value.ifEmpty {
                    if(selectedValue.symbol.value.value.isNotEmpty()) "Strike Price" else ""
                },
                modifier = Modifier.weight(1f)
            ) {
                selectedValue.strikePrice.value.value = it
                selectedValue.strikePrice.onValueChange?.invoke(it)
            }

            Dropdown(
                items = state.displayData.value.filterBy.expiriesDate.map { it.toString() },
                selectedItem = selectedValue.expiryDate.value.value.ifEmpty {
                    if(selectedValue.symbol.value.value.isNotEmpty()) "Expiry Date" else ""
                },
                modifier = Modifier.weight(1f)
            ) {
                selectedValue.expiryDate.value.value = it
                selectedValue.expiryDate.onValueChange?.invoke(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FutureOptionScreenPreview() {
//    FutureOptionScreen(
//        state = FutureOptionState(
//            searchBoxState = SearchBoxState(
//                value = mutableStateOf(""),
//            ) { },
//            optionList = mutableStateOf(arrayListOf("Text 1", "Text 2")),
//            selectedItem = mutableStateOf("Text 1")
//        )
//    )
}
