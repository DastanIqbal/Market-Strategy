package com.dastanapps.marketstrategy.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dastanapps.marketstrategy.db.table.OrderEntity

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM `Order`")
    fun getAllOrder(): List<OrderEntity>

    @Query("SELECT * FROM `Order` WHERE id = :id")
    fun getOrder(id: Int): OrderEntity
}

