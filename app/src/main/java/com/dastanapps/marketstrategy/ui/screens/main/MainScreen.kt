package com.dastanapps.marketstrategy.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

data class FutureOptionState(
    val searchBoxState: SearchBoxState,
    val optionList: MutableState<List<String>> = mutableStateOf(arrayListOf()),
    val selectedItem: MutableState<String> = mutableStateOf(""),
    val displayData: MutableState<FutureOptionIndicesData> = mutableStateOf(FutureOptionIndicesData.empty())
)

@Composable
fun FutureOptionScreen(
    state: FutureOptionState,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Row {
            Dropdown(
                items = state.optionList.value,
                selectedItem = state.selectedItem.value
            ){
                state.selectedItem.value = it
            }
        }
        Text(text = "Underlying Value: ${state.displayData.value.underlyingValue}",modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun FutureOptionScreenPreview() {
    FutureOptionScreen(
        state = FutureOptionState(
            searchBoxState = SearchBoxState(
                value = mutableStateOf(""),
            ) { },
            optionList = mutableStateOf(arrayListOf("Text 1", "Text 2")),
            selectedItem = mutableStateOf("Text 1")
        )
    )
}
