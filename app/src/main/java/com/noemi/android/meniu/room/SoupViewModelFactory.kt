package com.noemi.android.meniu.room

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class SoupViewModelFactory(private val database: OrderDataBase, private val soup: String): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SoupViewModel(database, soup) as T
    }
}