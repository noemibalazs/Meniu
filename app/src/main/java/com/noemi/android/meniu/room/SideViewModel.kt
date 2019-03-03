package com.noemi.android.meniu.room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel


internal class SideViewModel(database: OrderDataBase, garnitura: String): ViewModel() {

    val listOrdersBySide: LiveData<List<Order>>

    init {
        listOrdersBySide = database.orderDao().getListOrdersBySide(garnitura)
    }
}