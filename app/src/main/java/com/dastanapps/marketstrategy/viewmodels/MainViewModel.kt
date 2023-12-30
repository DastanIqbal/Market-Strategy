package com.dastanapps.marketstrategy.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dastanapps.marketstrategy.domain.GetDaysAverageUseCase
import com.dastanapps.marketstrategy.domain.models.GetDayAverageParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDaysAverageUseCase: GetDaysAverageUseCase
): ViewModel() {

    private val _indicesLiveData = MutableLiveData<String>()
    val indicesLiveData: LiveData<String> = _indicesLiveData

    fun getIndices() {
        viewModelScope.launch{
            val day50 = getDaysAverageUseCase.run(GetDayAverageParam("ITC",50)).average.toString()
            Log.d("GetDaysAverage", "50 Day : $day50")
            _indicesLiveData.postValue(day50)
            val day200 = getDaysAverageUseCase.run(GetDayAverageParam("ITC",200)).average.toString()
            Log.d("GetDaysAverage", "200 Day: $day200")
            _indicesLiveData.postValue(day200)
        }
    }
}