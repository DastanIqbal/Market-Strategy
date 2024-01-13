package com.dastanapps.marketstrategy.domain

import com.dastanapps.marketstrategy.data.models.FutureOptionIndicesData
import com.dastanapps.marketstrategy.data.nse.NSERepo
import com.dastanapps.marketstrategy.domain.base.UseCase
import com.dastanapps.marketstrategy.domain.models.FutureOptionDisplayB
import com.dastanapps.marketstrategy.domain.models.FutureOptionParam
import com.dastanapps.marketstrategy.domain.models.map
import javax.inject.Inject

/**
 * Author: Iqbal Ahmed
 * Date: 07/01/2024
 * Description: Brief description of the file or purpose.
 */

class FutureOptionUseCase @Inject constructor(
    private val repo: NSERepo,
) : UseCase<FutureOptionParam, FutureOptionIndicesData>() {
    override suspend fun run(param: FutureOptionParam?): FutureOptionIndicesData {
        return repo.fnoData(param!!)
    }
}