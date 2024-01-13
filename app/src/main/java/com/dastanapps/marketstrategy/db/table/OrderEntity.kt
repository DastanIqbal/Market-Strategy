package com.dastanapps.marketstrategy.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */

@Entity(tableName = "Order")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?=null,

    @ColumnInfo(name = "symbol")
    var symbol: String?=null,

    @ColumnInfo(name = "trade_type")
    var tradeType: String?=null,

    @ColumnInfo(name = "strike_price")
    var strikePrice: String?=null,

    @ColumnInfo(name = "expiry_date")
    var expiryDate: String?=null,

    @ColumnInfo(name = "json")
    var json: String?=null,

    @ColumnInfo(name = "status")
    var status: String?=null,

    @ColumnInfo(name = "created_at")
    var createdAt: Long=System.currentTimeMillis(),
)