package com.dastanapps.marketstrategy.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dastanapps.marketstrategy.data.models.FutureOptionIndicesData
import com.dastanapps.marketstrategy.domain.FutureOptionUseCase
import com.dastanapps.marketstrategy.domain.GetDaysAverageUseCase
import com.dastanapps.marketstrategy.domain.models.FutureOptionParam
import com.dastanapps.marketstrategy.domain.models.GetDayAverageParam
import com.dastanapps.marketstrategy.ui.screens.main.FutureOptionState
import com.dastanapps.marketstrategy.ui.theme.component.SearchBoxState
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

    private val _indicesLiveData = MutableLiveData<String>()
    val indicesLiveData: LiveData<String> = _indicesLiveData

    private val _futureOptionLiveData = MutableLiveData<FutureOptionIndicesData>()
    val futureOptionLiveData: LiveData<FutureOptionIndicesData> = _futureOptionLiveData

    val futureOptionList = mutableStateOf<List<String>>(arrayListOf())
    val selectedOptionItem = mutableStateOf("")
    val searchValue = mutableStateOf("")
    val futureOptionIndicesData = mutableStateOf(FutureOptionIndicesData.empty())

    val fustionOptionState by lazy {
        FutureOptionState(
            searchBoxState = SearchBoxState(
                value = searchValue
            ) {
                if (it.lowercase().contains("nifty")) {
                    futureOptionData(it.uppercase())
                }
            },
            optionList = futureOptionList,
            selectedItem = selectedOptionItem,
            displayData = futureOptionIndicesData
        )
    }

    fun getIndices() {
        viewModelScope.launch {
            val day50 = getDaysAverageUseCase.run(GetDayAverageParam("ITC", 50)).average.toString()
            Log.d("GetDaysAverage", "50 Day : $day50")
            _indicesLiveData.postValue(day50)
            val day200 =
                getDaysAverageUseCase.run(GetDayAverageParam("ITC", 200)).average.toString()
            Log.d("GetDaysAverage", "200 Day: $day200")
            _indicesLiveData.postValue(day200)
        }
    }

    fun futureOptionData(symbol: String) {
        viewModelScope.launch {
            try {
                val data = futureOptionUseCase.run(FutureOptionParam(symbol))
//                _futureOptionLiveData.postValue(data)
                futureOptionIndicesData.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun futureOptionList() {
        futureOptionList.value = arrayListOf("NIFTY", "BANKNIFTY", "FINNIFTY", "MIDCPNIFTY")
    }
}