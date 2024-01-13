package com.dastanapps.marketstrategy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dastanapps.marketstrategy.db.dao.OrderDao
import com.dastanapps.marketstrategy.db.table.OrderEntity

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */

@Database(entities = [OrderEntity::class], version = AppDatabase.DB_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

    companion object {
        const val DB_NAME = "app_db"
        const val DB_VERSION = 1

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}