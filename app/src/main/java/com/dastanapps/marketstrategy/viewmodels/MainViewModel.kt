package com.dastanapps.marketstrategy.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dastanapps.marketstrategy.data.models.FutureOptionIndicesData
import com.dastanapps.marketstrategy.data.models.OptionTypeData
import com.dastanapps.marketstrategy.db.AppDatabase
import com.dastanapps.marketstrategy.db.models.OrderStatus
import com.dastanapps.marketstrategy.db.table.OrderEntity
import com.dastanapps.marketstrategy.domain.FutureOptionUseCase
import com.dastanapps.marketstrategy.domain.GetDaysAverageUseCase
import com.dastanapps.marketstrategy.domain.models.FutureOptionDisplayB
import com.dastanapps.marketstrategy.domain.models.FutureOptionParam
import com.dastanapps.marketstrategy.domain.models.GetDayAverageParam
import com.dastanapps.marketstrategy.domain.models.map
import com.dastanapps.marketstrategy.ui.screens.main.FutureOptionState
import com.dastanapps.marketstrategy.ui.screens.main.models.TradeOption
import com.dastanapps.marketstrategy.ui.theme.component.SearchBoxState
import com.dastanapps.marketstrategy.viewmodels.models.SelectedValue
import com.dastanapps.marketstrategy.viewmodels.models.SelectedValueItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDaysAverageUseCase: GetDaysAverageUseCase,
    private val futureOptionUseCase: FutureOptionUseCase,
    private val database: AppDatabase
) : ViewModel() {

    private val futureOptionList = mutableStateOf<List<String>>(
        arrayListOf("NIFTY", "BANKNIFTY", "FINNIFTY", "MIDCPNIFTY")
    )
    val searchValue = mutableStateOf("")
    val futureOptionDisplayData = mutableStateOf(FutureOptionDisplayB.empty())

    private var _futureOptionIndicesData: FutureOptionIndicesData? = null

    private val selectedValue = SelectedValue(
        symbol = SelectedValueItem(mutableStateOf("")) {
            futureOptionData(it)
        },
        strikePrice = SelectedValueItem(mutableStateOf("")),
        expiryDate = SelectedValueItem(mutableStateOf("")),
    )

    var toastCallback: ((String) -> Unit)? = null
    var showHistory: (() -> Unit)? = null

    private val optionActionClick: (OptionTypeData, Int, TradeOption) -> Unit = { data,lots, trade ->
        viewModelScope.launch(Dispatchers.IO) {
            database.orderDao().insertOrder(
                OrderEntity(
                    symbol = selectedValue.symbol.value.value,
                    tradeType = trade.name,
                    optionType = data.type.name,
                    ltp = data.lastTradedPrice.toString(),
                    strikePrice = data.strikePrice.toString(),
                    expiryDate = data.expiryDate,
                    lots = lots,
                    json = data.toJson(),
                    status = OrderStatus.PENDING.name
                )
            )
            withContext(Dispatchers.Main) {
                toastCallback?.invoke("Order Executed")
            }
        }
    }


    val futureOptionState by lazy {
        FutureOptionState(
            searchBoxState = SearchBoxState(
                value = searchValue
            ) {
                futureOptionData(it.uppercase())
            },
            optionList = futureOptionList,
            selectedItem = selectedValue,
            displayData = futureOptionDisplayData,
            getQuotesClick = { getQuotes() },
            optionActionClick = optionActionClick,
            showHistory = { showHistory?.invoke() }
        )
    }

    fun getIndices() {
        viewModelScope.launch {
            val day50 = getDaysAverageUseCase.run(GetDayAverageParam("ITC", 50)).average.toString()
            Log.d("GetDaysAverage", "50 Day : $day50")
            val day200 =
                getDaysAverageUseCase.run(GetDayAverageParam("ITC", 200)).average.toString()
            Log.d("GetDaysAverage", "200 Day: $day200")
        }
    }

    fun futureOptionData(symbol: String) {
        futureOptionDisplayData.value = FutureOptionDisplayB.empty()
        viewModelScope.launch {
            try {
                val data = futureOptionUseCase.run(FutureOptionParam(symbol))
                _futureOptionIndicesData = data
                futureOptionDisplayData.value = data.map()
            } catch (e: Exception) {
                e.printStackTrace()
                toastCallback?.invoke("Unable to fetch data")
            }
        }
    }

    private fun getQuotes() {
        viewModelScope.launch {
            try {
                val data =
                    futureOptionUseCase.run(FutureOptionParam(selectedValue.symbol.value.value))
                _futureOptionIndicesData = data
                val list = _futureOptionIndicesData?.records?.filter {
                    it.strikePrice == selectedValue.strikePrice.value.value.toDouble() &&
                            it.expiryDate == selectedValue.expiryDate.value.value
                }
                futureOptionDisplayData.value.fnoCallPutList.value = list?.toList() ?: emptyList()
            }catch (e:Exception){
                e.printStackTrace()
                toastCallback?.invoke("Unable to fetch data")
            }
        }
    }
}