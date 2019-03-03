package com.noemi.android.meniu.room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel


internal class DateViewModel(database: OrderDataBase, date: String): ViewModel() {

    val listOrdersDate: LiveData<List<Order>>

    init {
        listOrdersDate = database.orderDao().getListOrdersByDate(date)
    }
}
