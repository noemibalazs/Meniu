package com.noemi.android.meniu.room

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class SideViewModelFactory(private val database: OrderDataBase, private val garnitura: String): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SideViewModel(database, garnitura) as T
    }
}