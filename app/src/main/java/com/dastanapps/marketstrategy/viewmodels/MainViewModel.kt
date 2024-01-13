package com.dastanapps.marketstrategy.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dastanapps.marketstrategy.data.models.FutureOptionIndicesData
import com.dastanapps.marketstrategy.data.models.OptionTypeData
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
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDaysAverageUseCase: GetDaysAverageUseCase,
    private val futureOptionUseCase: FutureOptionUseCase
) : ViewModel() {

    private val futureOptionList = mutableStateOf<List<String>>(
        arrayListOf("NIFTY", "BANKNIFTY", "FINNIFTY", "MIDCPNIFTY")
    )
    val searchValue = mutableStateOf("")
    val futureOptionDisplayData = mutableStateOf(FutureOptionDisplayB.empty())

    private var _futureOptionIndicesData: FutureOptionIndicesData? = null

    private val selectedValue = SelectedValue(
        symbol = SelectedValueItem(mutableStateOf("")){
            futureOptionData(it)
        },
        strikePrice = SelectedValueItem(mutableStateOf("")),
        expiryDate = SelectedValueItem(mutableStateOf("")),
    )

    private val getQuotesClick = {
        val list = _futureOptionIndicesData?.records?.filter {
            it.strikePrice == selectedValue.strikePrice.value.value.toDouble() &&
                    it.expiryDate == selectedValue.expiryDate.value.value
        }
        futureOptionDisplayData.value.fnoCallPutList.value = list?.toList()?: emptyList()
    }

    private val optionActionClick:(OptionTypeData, TradeOption) ->Unit = { data, trade ->
        Log.d("TAG", data.toString())
        Log.d("TAG", trade.toString())
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
            getQuotesClick = getQuotesClick,
            optionActionClick = optionActionClick
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
        viewModelScope.launch {
            try {
                val data = futureOptionUseCase.run(FutureOptionParam(symbol))
                _futureOptionIndicesData = data
                futureOptionDisplayData.value = data.map()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}