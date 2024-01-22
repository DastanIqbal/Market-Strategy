package com.dastanapps.marketstrategy.ui.screens.order.open

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dastanapps.marketstrategy.data.models.OptionTypeData
import com.dastanapps.marketstrategy.db.table.OrderEntity
import com.dastanapps.marketstrategy.ui.screens.main.models.TradeOption
import com.dastanapps.marketstrategy.utils.fromJson
import com.dastanapps.marketstrategy.viewmodels.OrdersViewModel

/**
 *
 * Created by Iqbal Ahmed on 17/01/2024
 *
 */

@Composable
fun OpenOrders(
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val refresh = remember { mutableStateOf(false) }
    val reloadata = remember { mutableStateOf(true) }
    if (refresh.value) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }
    if (reloadata.value) {
        viewModel.fetchOpenOrders()
        reloadata.value = false
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (viewModel.openOrders.value.isEmpty()) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "No Data Found", modifier = Modifier)
                }
            }
            return@LazyColumn
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    modifier = Modifier.clickable {
                        refresh.value = true
                        viewModel.refresh() {
                            refresh.value = false
                        }
                    }
                )
            }
        }
        items(viewModel.openOrders.value) {
            OpenOrderItem(it) {
                viewModel.closeOrder(it)
                reloadata.value = true
            }
        }
    }
}

@Composable
fun OpenOrderItem(entity: OrderEntity, closeOrderClick: (OrderEntity) -> Unit) {
    val optionTypeData = entity.json?.fromJson(OptionTypeData::class.java)!!
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(
                    text = "${entity.symbol}  ${optionTypeData.strikePrice}  ${optionTypeData.expiryDate}",
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    modifier = Modifier.padding(4.dp)
                )
            }
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

            Text(
                text = "Change: ${entity.ltpChange}", style = TextStyle(
                    color = when {
                        entity.ltpChange.toDouble() > 0 -> Color.Green
                        entity.ltpChange.toDouble() < 0 -> Color.Red
                        else -> Color.White
                    }
                ), modifier = Modifier.padding(8.dp)
            )

            Row(modifier = Modifier.padding(8.dp)) {
                Button(
                    modifier = Modifier.padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        closeOrderClick.invoke(entity)
                    }) {
                    if (entity.tradeType == TradeOption.BUY.name) {
                        Text(text = "Sell")
                    } else {
                        Text(text = "Buy")
                    }
                }

                Text(
                    text = "Lots: ${entity.lots}",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}



