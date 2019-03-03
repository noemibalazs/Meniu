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
import com.noemi.android.meniu.room.MainViewModel
import com.noemi.android.meniu.room.MainViewModelFactory
import com.noemi.android.meniu.room.OrderDataBase

class MainSearchActivity : AppCompatActivity() {

    private var myDatabase: OrderDataBase? = null
    private var myListView: ListView? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_search)

        myDatabase = OrderDataBase.getOrderDataBase(this)
        myListView = findViewById(R.id.main_list_view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        searchView = menu?.findItem(R.id.menu_search)!!.actionView as SearchView
        searchView?.setSubmitButtonEnabled(true)
        searchView?.setOnQueryTextListener(searchListener)
        return super.onCreateOptionsMenu(menu)
    }

    private var searchListener = object : SearchView.OnQueryTextListener{

        override fun onQueryTextChange(newText: String?): Boolean {
            getSearch(newText!!)
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            getSearch(query!!)
            return true
        }

        private fun getSearch(main: String){

            val text = "%" + main + "%"
            val mainFactory = MainViewModelFactory(myDatabase!!, text)
            val mainView = ViewModelProviders.of(this@MainSearchActivity, mainFactory).get(MainViewModel::class.java)
            mainView.listOrdersByMain.observe(this@MainSearchActivity, Observer {
                val myAdapter = AllOrdersAdapter(this@MainSearchActivity, it!!)
                myListView!!.adapter = myAdapter
            })
        }
    }
}
