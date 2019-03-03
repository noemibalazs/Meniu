package com.noemi.android.meniu.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


@Dao
interface OrderDAO {

    @Query("SELECT * FROM orders")
    fun getListOrders(): LiveData<List<Order>>

    @Query("SELECT * FROM orders WHERE data LIKE :dataSearch")
    fun getListOrdersByDate(dataSearch: String): LiveData<List<Order>>

    @Query("SELECT * FROM orders WHERE supa LIKE :soupSearch")
    fun getListOrdersBySoup(soupSearch: String): LiveData<List<Order>>

    @Query("SELECT * FROM orders WHERE feluldoi LIKE :searchMain")
    fun  getListOrdersByMain(searchMain: String): LiveData<List<Order>>

    @Query("SELECT * FROM orders WHERE garnitura LIKE :searchSide")
    fun getListOrdersBySide(searchSide: String): LiveData<List<Order>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: Order)
}