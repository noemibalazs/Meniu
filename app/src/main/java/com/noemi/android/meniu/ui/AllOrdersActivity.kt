package com.noemi.android.meniu.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.noemi.android.meniu.R
import com.noemi.android.meniu.adapter.AllOrdersAdapter
import com.noemi.android.meniu.room.OrderDataBase
import com.noemi.android.meniu.room.OrderViewModel

class AllOrdersActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var myDataBase: OrderDataBase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_orders)


        myDataBase = OrderDataBase.getOrderDataBase(this)
        listView = findViewById(R.id.list_view)

        val viewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
        viewModel.listOrders.observe(this, Observer {
            val myAdapter = AllOrdersAdapter(this, it!!)
            listView!!.adapter = myAdapter
        })
    }
}
