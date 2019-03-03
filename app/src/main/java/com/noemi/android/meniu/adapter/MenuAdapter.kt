package com.noemi.android.meniu.adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.noemi.android.meniu.fragment.MenuFragment
import com.noemi.android.meniu.model.Menu
import java.text.SimpleDateFormat
import java.util.*

class MenuAdapter(fragmentManager: FragmentManager, private var menus: MutableList<Menu> ): FragmentStatePagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {
        return MenuFragment.newInstance(menus[position])
    }

    override fun getCount(): Int {
      return menus.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val dataLong = menus[position].data
        val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
        val time = dateFormat.format(dataLong)
        val title = time.substring(0, time.length-1).toUpperCase()
        return title
    }
}