package com.dastanapps.marketstrategy.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dastanapps.marketstrategy.data.models.OptionTypeData
import com.dastanapps.marketstrategy.domain.models.FutureOptionDisplayB
import com.dastanapps.marketstrategy.ui.screens.main.models.TradeOption
import com.dastanapps.marketstrategy.ui.theme.component.Dropdown
import com.dastanapps.marketstrategy.ui.theme.component.NumberTextField
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
    val optionActionClick: (OptionTypeData, Int, TradeOption) -> Unit,
    val showHistory: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: FutureOptionState,
    modifier: Modifier = Modifier
) {
    val selectedValue = state.selectedItem
    val fnoCallPutList = state.displayData.value.fnoCallPutList
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = "Market Strategy") })
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) { innerPadding ->

        LazyColumn(modifier = modifier.padding(innerPadding)) {
            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Underlying Value: ${state.displayData.value.currentPrice}",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "History",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                            .clickable { state.showHistory.invoke() }
                    )
                }
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallPutItem(
    optionTypeData: OptionTypeData,
    optionActionClick: (OptionTypeData, Int, TradeOption) -> Unit
) {
    val numberState = remember { mutableStateOf(1) }
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(
                    text = "Option: ${optionTypeData.type.name}",
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "LTP: ${optionTypeData.lastTradedPrice}",
                    modifier = Modifier.padding(4.dp)
                )
                Text(text = "OI: ${optionTypeData.openInterest}", modifier = Modifier.padding(4.dp))
            }
            Row {
                Text(
                    text = "Bid Price: ${optionTypeData.bidPrice}",
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Qty: ${optionTypeData.bidQuantity}",
                    modifier = Modifier.padding(4.dp)
                )
            }

            Row {
                Text(
                    text = "Ask Price: ${optionTypeData.askPrice}",
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Qty: ${optionTypeData.askQuantity}",
                    modifier = Modifier.padding(4.dp)
                )
            }

            Row{
                Text(
                    text = "Lots:",
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(end = 8.dp)
                )
                NumberTextField(
                    text = numberState,
                    placeholderText = "Lots",
                    modifier = Modifier.width(50.dp),
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }



            Row(modifier = Modifier.padding(8.dp)) {
                Button(
                    modifier = Modifier.padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        optionActionClick.invoke(optionTypeData, numberState.value, TradeOption.BUY)
                    }) {
                    Text(text = "Buy")
                }

                Button(
                    modifier = Modifier,
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        optionActionClick.invoke(
                            optionTypeData,
                            numberState.value,
                            TradeOption.SELL
                        )
                    }) {
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
