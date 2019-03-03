package com.noemi.android.meniu.fragment


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.noemi.android.meniu.R
import com.noemi.android.meniu.model.Menu
import com.noemi.android.meniu.room.Order
import com.noemi.android.meniu.room.OrderDataBase
import com.noemi.android.meniu.room.OrderExecutor
import java.text.SimpleDateFormat
import java.util.*

class MenuFragment : Fragment() {

    private var click: Boolean = true
    private var orderSoup: String? = null
    private var orderMain: String? = null
    private var orderSide: String? = null

    companion object {

        var data: Long? = 0
        var soup1: String? = null
        var soup2: String? = null
        var soup3: String? = null
        var main1: String? = null
        var main2: String? = null
        var main3: String? = null
        var decor1: String? = null
        var decor2: String? = null

        fun newInstance(menu: Menu): MenuFragment {

            val fragment = MenuFragment()

            data = menu.data
            val list = menu.sort
            soup1 = list!![0].meal!!.name
            soup2 = list[1].meal!!.name
            soup3 = list[2].meal!!.name
            main1 = list[3].meal!!.name
            main2 = list[4].meal!!.name
            main3 = list[5].meal!!.name
            decor1 = list[6].meal!!.name
            decor2 = list[7].meal!!.name

            val bundle = Bundle()
            bundle.putLong(Constants.DATE, data!!)
            bundle.putString(Constants.SOUP_1, soup1)
            bundle.putString(Constants.SOUP_2, soup2)
            bundle.putString(Constants.SOUP_3, soup3)
            bundle.putString(Constants.MAIN_1, main1)
            bundle.putString(Constants.MAIN_2, main2)
            bundle.putString(Constants.MAIN_3, main3)
            bundle.putString(Constants.DECOR_1, decor1)
            bundle.putString(Constants.DECOR_2, decor2)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val myDatabase = OrderDataBase.getOrderDataBase(context!!)

        var uj = data

        val textSoup1 = view.findViewById<TextView>(R.id.soup_1)
        val textSoup2 = view.findViewById<TextView>(R.id.soup_2)
        val textSoup3 = view.findViewById<TextView>(R.id.soup_3)
        val textmain1 = view.findViewById<TextView>(R.id.main_1)
        val textmain2 = view.findViewById<TextView>(R.id.main_2)
        val textmain3 = view.findViewById<TextView>(R.id.main_3)
        val garnitura1 = view.findViewById<TextView>(R.id.decor_1)
        val garnitura2 = view.findViewById<TextView>(R.id.decor_2)

        val image1 = view.findViewById<ImageView>(R.id.image_1)
        val image2 = view.findViewById<ImageView>(R.id.image_2)
        val image3 = view.findViewById<ImageView>(R.id.image_3)
        val image4 = view.findViewById<ImageView>(R.id.image_4)
        val image5 = view.findViewById<ImageView>(R.id.image_5)
        val image6 = view.findViewById<ImageView>(R.id.image_6)
        val image7 = view.findViewById<ImageView>(R.id.image_7)
        val image8 = view.findViewById<ImageView>(R.id.image_8)

        val fb = view.findViewById<FloatingActionButton>(R.id.fb)

        textSoup1.setText(soup1)
        textSoup2.setText(soup2)
        textSoup3.setText(soup3)
        textmain1.setText(main1)
        textmain2.setText(main2)
        textmain3.setText(main3)
        garnitura1.setText(decor1)
        garnitura2.setText(decor2)

        val layout1 = view.findViewById<RelativeLayout>(R.id.layout_1)
        layout1.setOnClickListener{

            if (click){
                textSoup1.setTextColor(resources.getColor(R.color.grey))
                image1.setImageResource(R.drawable.checkbox_selected)
                orderSoup = soup1
                click = false
            }else {
                textSoup1.setTextColor(resources.getColor(R.color.faded_grey))
                image1.setImageResource(R.drawable.checkbox)
                click = true
            }

        }

        val layout2 = view.findViewById<RelativeLayout>(R.id.layout_2)
        layout2.setOnClickListener {
            if (click){
                textSoup2.setTextColor(resources.getColor(R.color.grey))
                image2.setImageResource(R.drawable.checkbox_selected)
                orderSoup = soup2
                click = false
            } else {
                textSoup2.setTextColor(resources.getColor(R.color.faded_grey))
                image2.setImageResource(R.drawable.checkbox)
                click = true
            }

        }

        val layout3 = view.findViewById<RelativeLayout>(R.id.layout_3)
        layout3.setOnClickListener {
            if (click){
                textSoup3.setTextColor(resources.getColor(R.color.grey))
                image3.setImageResource(R.drawable.checkbox_selected)
                orderSoup = soup3
                click = false
            }else{
                textSoup3.setTextColor(resources.getColor(R.color.faded_grey))
                image3.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        val layout4 = view.findViewById<RelativeLayout>(R.id.layout_4)
        layout4.setOnClickListener {
            if (click){
                textmain1.setTextColor(resources.getColor(R.color.grey))
                image4.setImageResource(R.drawable.checkbox_selected)
                orderMain = main1
                click = false
            }else {
                textmain1.setTextColor(resources.getColor(R.color.faded_grey))
                image4.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        val layout5 = view.findViewById<RelativeLayout>(R.id.layout_5)
        layout5.setOnClickListener {
            if (click){
                textmain2.setTextColor(resources.getColor(R.color.grey))
                image5.setImageResource(R.drawable.checkbox_selected)
                orderMain = main2
                click = false
            } else{
                textmain2.setTextColor(resources.getColor(R.color.faded_grey))
                image5.setImageResource(R.drawable.checkbox)
                click = true
            }

        }


        val layout6 = view.findViewById<RelativeLayout>(R.id.layout_6)
        layout6.setOnClickListener {
            if (click){
                textmain3.setTextColor(resources.getColor(R.color.grey))
                image6.setImageResource(R.drawable.checkbox_selected)
                orderMain = main3
                click = false
            } else {
                textmain3.setTextColor(resources.getColor(R.color.faded_grey))
                image6.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        val layout7 = view.findViewById<RelativeLayout>(R.id.layout_7)
        layout7.setOnClickListener {
            if (click){
                garnitura1.setTextColor(resources.getColor(R.color.grey))
                image7.setImageResource(R.drawable.checkbox_selected)
                orderSide = decor1
                click = false
            } else {
                garnitura1.setTextColor(resources.getColor(R.color.faded_grey))
                image7.setImageResource(R.drawable.checkbox)
                click = true
            }
        }


        val layout8 = view.findViewById<RelativeLayout>(R.id.layout_8)
        layout8.setOnClickListener {
            if (click){
                garnitura2.setTextColor(resources.getColor(R.color.grey))
                image8.setImageResource(R.drawable.checkbox_selected)
                orderSide = decor2
                click = false
            }else{
                garnitura2.setTextColor(resources.getColor(R.color.faded_grey))
                image8.setImageResource(R.drawable.checkbox)
                click = true
            }
        }

        fb.setOnClickListener {

            if ( orderSoup!=null && orderMain!=null && orderSide!=null){

                val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
                val ziuaComenzii = dateFormat.format(data)
                val ziua = ziuaComenzii.substring(0, ziuaComenzii.length-1).toUpperCase()
                val valueToShare = "Ati comandat in data de: $ziua \nSupa: $orderSoup \nFelul 2: $orderMain \nGarnitura: $orderSide"


                val formatForDB = SimpleDateFormat("EEEE, d LLLL yyyy", Locale.forLanguageTag("ro-RO"))
                val dataComenzii = formatForDB.format(data)
                val newDate = dataComenzii.substring(0, 1).toUpperCase() + dataComenzii.substring(1)

                OrderExecutor.getOrderExecutor().diskIO.execute{
                    val order = Order(newDate, orderSoup!!, orderMain!!, orderSide!!)
                    myDatabase.orderDao().insertOrder(order)
                }

                textSoup1.setTextColor(resources.getColor(R.color.faded_grey))
                image1.setImageResource(R.drawable.checkbox)
                textSoup2.setTextColor(resources.getColor(R.color.faded_grey))
                image2.setImageResource(R.drawable.checkbox)
                textSoup3.setTextColor(resources.getColor(R.color.faded_grey))
                image3.setImageResource(R.drawable.checkbox)
                textmain1.setTextColor(resources.getColor(R.color.faded_grey))
                image4.setImageResource(R.drawable.checkbox)
                textmain2.setTextColor(resources.getColor(R.color.faded_grey))
                image5.setImageResource(R.drawable.checkbox)
                textmain3.setTextColor(resources.getColor(R.color.faded_grey))
                image6.setImageResource(R.drawable.checkbox)
                garnitura1.setTextColor(resources.getColor(R.color.faded_grey))
                image7.setImageResource(R.drawable.checkbox)
                garnitura2.setTextColor(resources.getColor(R.color.faded_grey))
                image8.setImageResource(R.drawable.checkbox)

                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Queens's order")
                intent.putExtra(Intent.EXTRA_TEXT, valueToShare)
                startActivity(Intent.createChooser(intent, "Share via...."))
            }
            else{
                Toast.makeText(context, "You need to select at least one from each meal type", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }

        return view
    }




}
