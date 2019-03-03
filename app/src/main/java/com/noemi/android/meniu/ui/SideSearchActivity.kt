package com.noemi.android.meniu.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.ListView
import com.noemi.android.meniu.R
import com.noemi.android.meniu.adapter.AllOrdersAdapter
import com.noemi.android.meniu.room.OrderDataBase
import com.noemi.android.meniu.room.SideViewModel
import com.noemi.android.meniu.room.SideViewModelFactory

class SideSearchActivity : AppCompatActivity() {

    private var myDatabase: OrderDataBase? = null
    private var listView: ListView? = null
    private var searchView: SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_search_activity)

        myDatabase = OrderDataBase.getOrderDataBase(this)
        listView = findViewById(R.id.side_list_view)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        searchView = menu?.findItem(R.id.menu_search)!!.actionView as SearchView
        searchView?.setSubmitButtonEnabled(true)
        searchView?.setOnQueryTextListener(listener)
        return super.onCreateOptionsMenu(menu)
    }


    private var listener = object : SearchView.OnQueryTextListener{

        override fun onQueryTextChange(newText: String?): Boolean {
            getSideDish(newText!!)
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            getSideDish(query!!)
            return true
        }

        fun getSideDish(garnitura: String){

            val text = "%" + garnitura + "%"
            val garFactory = SideViewModelFactory(myDatabase!!, text)
            val viewModel = ViewModelProviders.of(this@SideSearchActivity, garFactory).get(SideViewModel::class.java)
            viewModel.listOrdersBySide.observe(this@SideSearchActivity, Observer {
                val myAdapter = AllOrdersAdapter(this@SideSearchActivity, it!!)
                listView!!.adapter = myAdapter
            })
        }
    }
}
