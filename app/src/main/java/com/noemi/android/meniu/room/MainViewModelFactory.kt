package com.noemi.android.meniu.room

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class MainViewModelFactory(private val database: OrderDataBase, private val main: String): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(database, main) as T
    }
}