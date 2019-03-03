package com.noemi.android.meniu.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = [Order::class], version = 1, exportSchema = false)
abstract class OrderDataBase : RoomDatabase() {

    companion object {

        private var sInstance: OrderDataBase? = null
        private val LOCK = Any()

        fun getOrderDataBase(context: Context):OrderDataBase{

            if (sInstance == null){
                synchronized(LOCK){
                    sInstance = Room.databaseBuilder(context.applicationContext,
                            OrderDataBase::class.java,
                            "order_database").build()
                }
            }
            return sInstance!!
        }

    }

    abstract fun orderDao() :OrderDAO
}
