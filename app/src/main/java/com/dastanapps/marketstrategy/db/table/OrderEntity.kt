package com.dastanapps.marketstrategy.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dastanapps.marketstrategy.data.models.OptionTypeData
import com.dastanapps.marketstrategy.domain.models.FutureOptionDisplayB
import com.dastanapps.marketstrategy.utils.fromJson

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

    @ColumnInfo(name = "option_type")
    var optionType: String?=null,

    @ColumnInfo(name = "ltp")
    var ltp: String?=null,

    @ColumnInfo(name = "symbol")
    var symbol: String?=null,

    @ColumnInfo(name = "trade_type")
    var tradeType: String?=null,

    @ColumnInfo(name = "strike_price")
    var strikePrice: String?=null,

    @ColumnInfo(name = "expiry_date")
    var expiryDate: String?=null,

    @ColumnInfo(name = "lots")
    var lots: Int?=null,

    @ColumnInfo(name = "json")
    var json: String?=null,

    @ColumnInfo(name = "status")
    var status: String?=null,

    @ColumnInfo(name = "created_at")
    var createdAt: Long=System.currentTimeMillis(),
){
    var ltpChange:String = "0.00"
    fun fromJson(): OptionTypeData {
        return json?.fromJson(OptionTypeData::class.java)!!
    }
}