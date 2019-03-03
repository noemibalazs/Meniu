package com.noemi.android.meniu.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.noemi.android.meniu.model.Menu
import kotlinx.android.synthetic.main.recycle_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class MenuViewHolder(val view: View, var menu: Menu? = null) : RecyclerView.ViewHolder(view){

    fun bindMenu(menu : Menu){

        with(menu){

            val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
            val time = dateFormat.format(menu.data)
            val title = time.substring(0, time.length-1).toUpperCase()
            view.rv_date.setText(title)

            val menuList = menu.sort
            view.rv_soup_1.setText(menuList!![0].meal!!.name)
            view.rv_soup_2.setText(menuList[1].meal!!.name)
            view.rv_soup_3.setText(menuList[2].meal!!.name)

            view.rv_main_1.setText(menuList[3].meal!!.name)
            view.rv_main_2.setText(menuList[4].meal!!.name)
            view.rv_main_3.setText(menuList[5].meal!!.name)

            view.rv_decor_1.setText(menuList[6].meal!!.name)
            view.rv_decor_2.setText(menuList[7].meal!!.name)

            view.rv_soup_price1.setText(menuList[0].meal!!.price.toString())
            view.rv_soup_price2.setText(menuList[1].meal!!.price.toString())
            view.rv_soup_price3.setText(menuList[2].meal!!.price.toString())

            view.rv_main_price1.setText(menuList[3].meal!!.price.toString())
            view.rv_main_price2.setText(menuList[4].meal!!.price.toString())
            view.rv_main_price3.setText(menuList[5].meal!!.price.toString())

            view.rv_decor_price1.setText(menuList[6].meal!!.price.toString())
            view.rv_decor_price2.setText(menuList[7].meal!!.price.toString())
        }
    }
}