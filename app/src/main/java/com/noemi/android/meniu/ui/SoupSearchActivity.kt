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
import com.noemi.android.meniu.room.SoupViewModel
import com.noemi.android.meniu.room.SoupViewModelFactory

class SoupSearchActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var myDatabase: OrderDataBase? = null
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soup_search)

        myDatabase = OrderDataBase.getOrderDataBase(this)
        listView = findViewById(R.id.soup_list_view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        searchView = menu?.findItem(R.id.menu_search)!!.actionView as SearchView
        searchView?.setSubmitButtonEnabled(true)
        searchView?.setOnQueryTextListener(myListener)
        return super.onCreateOptionsMenu(menu)
    }

    private var myListener = object : SearchView.OnQueryTextListener{

        override fun onQueryTextChange(newText: String?): Boolean {
            getSoupSearch(newText!!)
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            getSoupSearch(query!!)
            return true
        }

        private fun getSoupSearch(soup: String){

            val mySoup = "%" + soup + "%"
            val soupViewModel = SoupViewModelFactory(myDatabase!!, mySoup)
            val myView = ViewModelProviders.of(this@SoupSearchActivity, soupViewModel).get(SoupViewModel::class.java)
            myView.listOrdersBySoup.observe(this@SoupSearchActivity, Observer {

                val myAdapter = AllOrdersAdapter(this@SoupSearchActivity, it!!)
                listView!!.adapter = myAdapter
            })

        }
    }
}
