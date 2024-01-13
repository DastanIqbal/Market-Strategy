package com.dastanapps.marketstrategy.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.dastanapps.marketstrategy.data.models.FilterBy
import com.dastanapps.marketstrategy.data.models.FutureOptionIndicesData
import com.dastanapps.marketstrategy.data.models.OptionTypeData

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */

data class FutureOptionDisplayB(
    val currentPrice: Double,
    val filterBy: FilterBy,
    val fnoCallPutList: MutableState<List<OptionTypeData>>
) {
    companion object {
        fun empty(): FutureOptionDisplayB {
            return FutureOptionDisplayB(0.0,FilterBy.empty(), mutableStateOf(emptyList()))
        }
    }
}


fun FutureOptionIndicesData.map(): FutureOptionDisplayB {
    return FutureOptionDisplayB(this.underlyingValue, this.filterBy, mutableStateOf(emptyList()))
}