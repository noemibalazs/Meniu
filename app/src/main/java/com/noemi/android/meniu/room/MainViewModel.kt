package com.noemi.android.meniu.room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel


internal class MainViewModel(database: OrderDataBase, main: String): ViewModel() {

    val listOrdersByMain: LiveData<List<Order>>

    init {
        listOrdersByMain = database.orderDao().getListOrdersByMain(main)
    }
}