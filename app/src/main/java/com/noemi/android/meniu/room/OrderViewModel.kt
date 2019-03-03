package com.noemi.android.meniu.room

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData


class OrderViewModel(application: Application): AndroidViewModel(application) {

    val listOrders: LiveData<List<Order>>

    init {
        val orderDB = OrderDataBase.getOrderDataBase(application)
        listOrders = orderDB.orderDao().getListOrders()
    }
}