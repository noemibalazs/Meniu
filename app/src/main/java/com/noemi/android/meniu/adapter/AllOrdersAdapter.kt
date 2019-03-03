package com.noemi.android.meniu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.noemi.android.meniu.R
import com.noemi.android.meniu.room.Order


class AllOrdersAdapter(context: Context, allListOrders: List<Order>): ArrayAdapter<Order>(context, 0, allListOrders) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.all_item, parent, false)
        }

        val order = getItem(position)

        val allData = view!!.findViewById<TextView>(R.id.all_data)
        allData.setText(order.data)

        val allSoup = view.findViewById<TextView>(R.id.all_fel_1)
        allSoup.setText(order.supa)

        val allMain = view.findViewById<TextView>(R.id.all_fel_2)
        allMain.setText(order.feluldoi)

        val garnitura = view.findViewById<TextView>(R.id.all_decor)
        garnitura.setText(order.garnitura)


        return view

    }
}