package com.dastanapps.marketstrategy.ui.screens.order.close

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dastanapps.marketstrategy.data.models.OptionTypeData
import com.dastanapps.marketstrategy.db.table.OrderEntity
import com.dastanapps.marketstrategy.ui.theme.component.Empty
import com.dastanapps.marketstrategy.utils.fromJson
import com.dastanapps.marketstrategy.viewmodels.OrdersViewModel

/**
 *
 * Created by Iqbal Ahmed on 17/01/2024
 *
 */

@Composable
fun CloseOrders(
    viewModel: OrdersViewModel = hiltViewModel()
) {
    viewModel.fetchCloseOrders()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (viewModel.closeOrders.value.isEmpty()) {
            item {
                Empty()
            }
            return@LazyColumn
        }
        items(viewModel.closeOrders.value) {
            CloseOrderItem(it)
        }
    }
}


@Composable
fun CloseOrderItem(entity: OrderEntity) {
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
                    text = "${entity.symbol} ${optionTypeData.strikePrice} ${optionTypeData.expiryDate}",
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

            Row {
                Text(
                    text = "Lots: ${entity.lots}",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}