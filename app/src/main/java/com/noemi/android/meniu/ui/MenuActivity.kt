package com.noemi.android.meniu.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import com.google.firebase.database.*
import com.noemi.android.meniu.R
import com.noemi.android.meniu.adapter.MenuAdapter
import com.noemi.android.meniu.adapter.MenuPageTransformer
import com.noemi.android.meniu.model.Menu
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*


class MenuActivity : AppCompatActivity() {

    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null

    private var database: DatabaseReference? = null
    private var childEventListener: ChildEventListener? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.toolbar)

        database = FirebaseDatabase.getInstance().reference.child("queens_menu")
        val menuList: MutableList<Menu> = mutableListOf()

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        val paddingPx = dpToPixel(32) ?: 0
        val marginPx = dpToPixel(24) ?: 0

        viewPager!!.offscreenPageLimit = 7
        viewPager!!.clipToPadding = false
        viewPager!!.setPadding(paddingPx, 0, paddingPx, 0)
        viewPager!!.pageMargin = marginPx
        viewPager!!.setPageTransformer(true, dpToPixel(180).let { MenuPageTransformer(it) })


        val adapter = MenuAdapter(supportFragmentManager, menuList)
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)

        back_arrow.setOnClickListener {
            val intent = Intent(this, LaunchActivity::class.java)
            startActivity(intent)
        }

        jump_to.setOnClickListener {

            val title = getCurrentDateTitle()
            val size = adapter.count
            for (x in 0 until size){

                if (title.equals(adapter.getPageTitle(x))){
                    viewPager!!.currentItem = x
                }
            }
        }

        childEventListener = object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                menu = dataSnapshot.getValue(Menu::class.java)
                menuList.add(menu!!)
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {}

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {}

            override fun onChildRemoved(p0: DataSnapshot) {}

        }

        database!!.addChildEventListener(childEventListener!!)

    }

    override fun onStop() {
        super.onStop()
        if (childEventListener != null) {
            database!!.removeEventListener(childEventListener!!)
        }
    }

    private fun dpToPixel(dp: Int): Int {
        return (dp * this.resources.displayMetrics.density).toInt()
    }

    private fun getCurrentDateTitle():String {
        val calendar = Calendar.getInstance()
        val currentDate = calendar.timeInMillis
        val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
        val time = dateFormat.format(currentDate)
        val title = time.substring(0, time.length-1).toUpperCase()
        return title
    }

}
