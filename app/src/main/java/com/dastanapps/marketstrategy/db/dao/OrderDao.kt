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
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM `Order`")
    suspend fun getAllOrder(): List<OrderEntity>

    @Query("SELECT * FROM `Order` WHERE id = :id")
    suspend fun getOrder(id: Int): OrderEntity
    @Query("SELECT * FROM `Order` WHERE status = :status ORDER BY id DESC")
    suspend fun getOrdersByStatus(status: String) : List<OrderEntity>

    @Query("UPDATE `Order` SET status = :status WHERE id = :id")
    suspend fun closeOrder(id: Int?, status: String)

    @Query("SELECT DISTINCT symbol FROM `Order`")
    suspend fun getSymbols():List<String>
}

