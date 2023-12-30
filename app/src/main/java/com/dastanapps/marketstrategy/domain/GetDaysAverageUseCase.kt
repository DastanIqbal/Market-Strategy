package com.dastanapps.marketstrategy.domain

import com.dastanapps.marketstrategy.data.NSERepo
import com.dastanapps.marketstrategy.data.models.EquityData
import com.dastanapps.marketstrategy.domain.base.UseCase
import com.dastanapps.marketstrategy.domain.models.GetDayAverageParam
import com.dastanapps.marketstrategy.domain.models.GetDaysAverageB
import com.dastanapps.marketstrategy.utils.currentDateTime
import com.dastanapps.marketstrategy.utils.minusDays
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

class GetDaysAverageUseCase @Inject constructor(
    private val repo: NSERepo
) : UseCase<GetDayAverageParam, GetDaysAverageB>() {
    override suspend fun run(param: GetDayAverageParam?): GetDaysAverageB {
        val to = currentDateTime()
        val from = to.minusDays(param?.days?.toLong()!! + 20)
        return GetDaysAverageB(
            repo.historicalData(
                param.symbol,
                from,
                to
            ).data.take(param.days).calculateMovingAverage()
        )
    }

    private fun List<EquityData>.calculateMovingAverage(): Double {
        var totalSum = 0.0
        this.forEach {
            totalSum += it.price.toDouble()
        }
        return totalSum / this.size
    }
}