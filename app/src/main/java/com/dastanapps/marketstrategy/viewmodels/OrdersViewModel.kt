package com.dastanapps.marketstrategy.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dastanapps.marketstrategy.data.models.OptionType
import com.dastanapps.marketstrategy.db.AppDatabase
import com.dastanapps.marketstrategy.db.models.OrderStatus
import com.dastanapps.marketstrategy.db.table.OrderEntity
import com.dastanapps.marketstrategy.domain.FutureOptionUseCase
import com.dastanapps.marketstrategy.domain.models.FutureOptionParam
import com.dastanapps.marketstrategy.utils.roundTo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.MathContext
import java.text.DecimalFormat
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 17/01/2024
 *
 */

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val appDatabase: AppDatabase,
    private val useCase: FutureOptionUseCase
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

    fun refresh(block: () -> Unit) {
        viewModelScope.launch {
            val _this = this@OrdersViewModel
            val symbols = appDatabase.orderDao().getSymbols()
            val results = arrayListOf<OrderEntity>()
            symbols.forEach {
                val result = useCase.run(FutureOptionParam(it))
                val list = openOrders.value.filter { item -> item.symbol == it }

                list.filter { order ->
                    if (order.optionType == OptionType.CALL.name) {
                        val filteredData = result.records.filter {
                            it.type == OptionType.CALL &&
                                    it.strikePrice.toString() == order.strikePrice &&
                                    it.expiryDate == order.expiryDate
                        }
                        filteredData.map {
                            order.ltpChange = (-0.234242).roundTo() //it.change.roundTo()
                        }
                        results.add(order)
                    } else {
                        val filteredData = result.records.filter {
                            it.type == OptionType.CALL &&
                                    it.strikePrice.toString() == order.strikePrice &&
                                    it.expiryDate == order.expiryDate
                        }
                        filteredData.map {
                            order.ltpChange = 0.234242.roundTo()//it.change.roundTo()
                        }
                         results.add(order)
                    }
                }
            }

            openOrders.value = results
            block.invoke()
        }
    }
}
