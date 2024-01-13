package com.dastanapps.marketstrategy.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dastanapps.marketstrategy.data.models.OptionTypeData
import com.dastanapps.marketstrategy.domain.models.FutureOptionDisplayB
import com.dastanapps.marketstrategy.ui.screens.main.models.TradeOption
import com.dastanapps.marketstrategy.ui.theme.component.Dropdown
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
    val displayData: MutableState<FutureOptionDisplayB>,
    val getQuotesClick: () -> Unit,
    val optionActionClick: (OptionTypeData, TradeOption) -> Unit
)

@Composable
fun FutureOptionScreen(
    state: FutureOptionState,
    modifier: Modifier = Modifier
) {
    val selectedValue = state.selectedItem
    val fnoCallPutList = state.displayData.value.fnoCallPutList
    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = "Underlying Value: ${state.displayData.value.currentPrice}",
                modifier = Modifier.padding(16.dp)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Dropdown(
                    items = state.optionList.value,
                    selectedItem = selectedValue.symbol.value.value.ifEmpty { "Symbol" },
                    modifier = Modifier
                ) {
                    selectedValue.symbol.value.value = it
                    selectedValue.symbol.onValueChange?.invoke(it)
                }

                Dropdown(
                    items = state.displayData.value.filterBy.strikePrice.map { it.toString() },
                    selectedItem = selectedValue.strikePrice.value.value.ifEmpty {
                        if (selectedValue.symbol.value.value.isNotEmpty()) "Strike Price" else ""
                    },
                    modifier = Modifier
                ) {
                    selectedValue.strikePrice.value.value = it
                    selectedValue.strikePrice.onValueChange?.invoke(it)
                }

                Dropdown(
                    items = state.displayData.value.filterBy.expiriesDate.map { it.toString() },
                    selectedItem = selectedValue.expiryDate.value.value.ifEmpty {
                        if (selectedValue.symbol.value.value.isNotEmpty()) "Expiry Date" else ""
                    },
                    modifier = Modifier
                ) {
                    selectedValue.expiryDate.value.value = it
                    selectedValue.expiryDate.onValueChange?.invoke(it)
                }
            }


            if (selectedValue.canShowQuote()) {
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        state.getQuotesClick.invoke()
                    }
                ) {
                    Text(text = "Get Quotes")
                }
            }
        }
        items(fnoCallPutList.value) {
            CallPutItem(optionTypeData = it, state.optionActionClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallPutItem(
    optionTypeData: OptionTypeData,
    optionActionClick: (OptionTypeData, TradeOption) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Option: ${optionTypeData.type.name}", modifier = Modifier.padding(4.dp))
            Text(text = "LTP: ${optionTypeData.lastTradedPrice}", modifier = Modifier.padding(4.dp))
            Text(text = "Bid Price: ${optionTypeData.bidPrice}", modifier = Modifier.padding(4.dp))
            Text(
                text = "Bid Quantity: ${optionTypeData.bidQuantity}",
                modifier = Modifier.padding(4.dp)
            )
            Text(text = "Ask Price: ${optionTypeData.askPrice}", modifier = Modifier.padding(4.dp))
            Text(
                text = "Ask Quantity: ${optionTypeData.askQuantity}",
                modifier = Modifier.padding(4.dp)
            )

            Row(modifier = Modifier.padding(8.dp)) {
                Button(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = { optionActionClick.invoke(optionTypeData, TradeOption.BUY) }) {
                    Text(text = "Buy")
                }

                Button(onClick = { optionActionClick.invoke(optionTypeData, TradeOption.SELL) }) {
                    Text(text = "Sell")
                }
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
