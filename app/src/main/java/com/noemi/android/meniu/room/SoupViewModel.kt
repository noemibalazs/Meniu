package com.noemi.android.meniu.room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel


internal class SoupViewModel(database: OrderDataBase, soup: String): ViewModel() {

    val listOrdersBySoup: LiveData<List<Order>>

    init {
        listOrdersBySoup = database.orderDao().getListOrdersBySoup(soup)
    }
}
