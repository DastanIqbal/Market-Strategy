package com.dastanapps.marketstrategy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dastanapps.marketstrategy.data.NSERepo
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
    private val repository: NSERepo
): ViewModel() {

    private val _indicesLiveData = MutableLiveData<String>()
    val indicesLiveData: LiveData<String> = _indicesLiveData

    fun getIndices() {
        viewModelScope.launch{
            _indicesLiveData.postValue(repository.getIndices())
        }
    }
}