package com.dastanapps.marketstrategy.domain.base

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

abstract class UseCase<T, E> {
    abstract suspend fun run(param: T?): E
}