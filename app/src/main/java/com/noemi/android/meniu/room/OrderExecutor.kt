package com.noemi.android.meniu.room

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class OrderExecutor private constructor(val diskIO: Executor, val networkIO: Executor, val mainThread: Executor) {

    private class MainThreadExecutor: Executor {

        private val handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            handler.post(command)
        }
    }

    companion object {
        private val LOCK = Any()
        private var sInstance: OrderExecutor? = null


        fun getOrderExecutor():OrderExecutor{
            if (sInstance == null){
                synchronized(LOCK){
                    sInstance= OrderExecutor(Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(3),
                            MainThreadExecutor())
                }
            }
            return sInstance!!
        }
    }
}
