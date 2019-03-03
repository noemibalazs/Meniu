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
import com.noemi.android.meniu.room.DateViewModel
import com.noemi.android.meniu.room.DateViewModelFactory
import com.noemi.android.meniu.room.OrderDataBase

class DataSearchActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var myDataBase: OrderDataBase? = null
    private var searchView: SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_search)

        myDataBase = OrderDataBase.getOrderDataBase(this)
        listView = findViewById(R.id.data_list_view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu, menu)
        searchView = menu?.findItem(R.id.menu_search)!!.actionView as SearchView
        searchView?.setSubmitButtonEnabled(true)
        searchView?.setOnQueryTextListener(searchListener)
        return super.onCreateOptionsMenu(menu)
    }


    private var searchListener = object: SearchView.OnQueryTextListener{

        override fun onQueryTextChange(newText: String?): Boolean {
            getSearch(newText!!)
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            getSearch(query!!)
            return true
        }

        private fun getSearch(text: String){

            val mySearch = "%" + text + "%"
            val myViewModel = DateViewModelFactory(myDataBase!!, mySearch)
            val model = ViewModelProviders.of(this@DataSearchActivity, myViewModel).get(DateViewModel::class.java)
            model.listOrdersDate.observe(this@DataSearchActivity, Observer {
                val myAdapter = AllOrdersAdapter(this@DataSearchActivity, it!!)
                listView!!.adapter = myAdapter

            })
        }
    }
}
