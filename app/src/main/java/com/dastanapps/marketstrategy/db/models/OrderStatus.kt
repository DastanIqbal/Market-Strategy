package com.dastanapps.marketstrategy.db.models

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */

enum class OrderStatus {
    UNKNOWN,
    PENDING,
    EXECUTED,
    CLOSED,
    CANCELLED,
    REJECTED,
    EXPIRED,
    COMPLETED
}