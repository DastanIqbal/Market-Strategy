package com.dastanapps.marketstrategy.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dastanapps.marketstrategy.db.AppDatabase
import com.dastanapps.marketstrategy.db.models.OrderStatus
import com.dastanapps.marketstrategy.db.table.OrderEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 17/01/2024
 *
 */

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {

    val openOrders = mutableStateOf<List<OrderEntity>>(emptyList())
    val closeOrders = mutableStateOf<List<OrderEntity>>(emptyList())
    fun fetchOpenOrders() {
        viewModelScope.launch {
            openOrders.value = appDatabase.orderDao().getOrdersByStatus(OrderStatus.PENDING.name)
        }
    }

    fun fetchCloseOrders() {
        viewModelScope.launch {
            closeOrders.value = appDatabase.orderDao().getOrdersByStatus(OrderStatus.COMPLETED.name)
        }
    }

    fun closeOrder(entity: OrderEntity) {
        viewModelScope.launch {
            appDatabase.orderDao().closeOrder(entity.id, OrderStatus.COMPLETED.name)
        }
    }
}