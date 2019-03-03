package com.noemi.android.meniu.ui

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.noemi.android.meniu.R
import com.noemi.android.meniu.adapter.MenuViewHolder
import com.noemi.android.meniu.model.Course
import com.noemi.android.meniu.model.CourseType
import com.noemi.android.meniu.model.Meal
import com.noemi.android.meniu.model.Menu
import java.text.SimpleDateFormat
import java.util.*


class AdminActivity : AppCompatActivity() {

    private var dateLong: Long = 0
    private var dateChild: String = ""
    internal val calendarDate = Calendar.getInstance()

    private var editDate: EditText? = null
    private var sup1: EditText? = null
    private var pretSup1: EditText? = null
    private var sup2: EditText? = null
    private var pretSup2: EditText? = null
    private var sup3: EditText? = null
    private var pretSup3: EditText? = null
    private var fel1: EditText? = null
    private var pretFel1: EditText? = null
    private var fel2: EditText? = null
    private var pretFel2: EditText? = null
    private var fel3: EditText? = null
    private var pretFel3: EditText? = null
    private var garn1: EditText? = null
    private var pretGarn1: EditText? = null
    private var garn2: EditText? = null
    private var pretGarn2: EditText? = null

    private var fb: FloatingActionButton? = null
    private var recycleView: RecyclerView? = null

    private var database: DatabaseReference? = null
    private var childEventListener: ChildEventListener? = null

    private var adapter: FirebaseRecyclerAdapter<Menu, MenuViewHolder>? = null

    private var key: String? = null
    private var dataFire: Long? = 0

    private var soup1: String? = null
    private var soup2: String? = null
    private var soup3: String? = null
    private var main1: String? = null
    private var main2: String? = null
    private var main3: String? = null
    private var decor1: String? = null
    private var decor2: String? = null

    private var pretSupa1: Float = 0.0f
    private var pretSupa2: Float = 0.0f
    private var pretSupa3: Float = 0.0f
    private var pretMain1: Float = 0.0f
    private var pretMain2: Float = 0.0f
    private var pretMain3: Float = 0.0f
    private var pretDecor1: Float = 0.0f
    private var pretDecor2: Float = 0.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        database = FirebaseDatabase.getInstance().reference.child("queens_menu")

        recycleView = findViewById(R.id.admin_recycle_view)
        recycleView!!.setHasFixedSize(true)
        recycleView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        fb = findViewById(R.id.admin_fb)
        fb!!.setOnClickListener {
            addMenuToFirebase()
        }

        childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                adapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        }

        database!!.addChildEventListener(childEventListener!!)


        val options = FirebaseRecyclerOptions.Builder<Menu>()
                .setQuery(database!!, Menu::class.java)
                .setLifecycleOwner(this)
                .build()

        adapter = object : FirebaseRecyclerAdapter<Menu, MenuViewHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
                val layout = LayoutInflater.from(this@AdminActivity).inflate(R.layout.recycle_item, parent, false)
                return MenuViewHolder(layout)
            }

            override fun onBindViewHolder(holder: MenuViewHolder, position: Int, model: Menu) {
                holder.bindMenu(model)

                holder.view.setOnClickListener {

                    key = getRef(position).key
                    dataFire = model.data
                    val lista = model.sort
                    soup1 = lista!![0].meal!!.name
                    soup2 = lista[1].meal!!.name
                    soup3 = lista[2].meal!!.name
                    main1 = lista[3].meal!!.name
                    main2 = lista[4].meal!!.name
                    main3 = lista[5].meal!!.name
                    decor1 = lista[6].meal!!.name
                    decor2 = lista[7].meal!!.name

                    pretSupa1 = lista[0].meal!!.price
                    pretSupa2 = lista[1].meal!!.price
                    pretSupa3 = lista[2].meal!!.price
                    pretMain1 = lista[3].meal!!.price
                    pretMain2 = lista[4].meal!!.price
                    pretMain3 = lista[5].meal!!.price
                    pretDecor1 = lista[6].meal!!.price
                    pretDecor2 = lista[7].meal!!.price


                    updateDelete()
                }
            }
        }

        recycleView!!.adapter = adapter

    }

    override fun onStop() {
        super.onStop()
        if (childEventListener != null) {
            database!!.removeEventListener(childEventListener!!)
        }
    }

    private fun addMenuToFirebase() {

        val dialog = AlertDialog.Builder(this).create()
        val layout = layoutInflater.inflate(R.layout.add_menu, null)

        editDate = layout.findViewById(R.id.edit_date)
        editDate!!.setOnClickListener {

            val date = DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendarDate.set(Calendar.YEAR, year)
                calendarDate.set(Calendar.MONTH, monthOfYear)
                calendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
                val time = dateFormat.format(calendarDate.timeInMillis)

                editDate!!.setText(time.substring(0, time.length - 1).toUpperCase())
                dateLong = calendarDate.timeInMillis
                dateChild = dateLong.toString()
                Log.d("Admin Activity", dateChild)

            }

            DatePickerDialog(this@AdminActivity, date, calendarDate.get(Calendar.YEAR),
                    calendarDate.get(Calendar.MONTH), calendarDate.get(Calendar.DAY_OF_MONTH)).show()
        }

        sup1 = layout.findViewById<EditText>(R.id.supa_1)
        pretSup1 = layout.findViewById<EditText>(R.id.supa_pret_1)

        sup2 = layout.findViewById<EditText>(R.id.supa_2)
        pretSup2 = layout.findViewById<EditText>(R.id.supa_pret_2)

        sup3 = layout.findViewById<EditText>(R.id.supa_3)
        pretSup3 = layout.findViewById<EditText>(R.id.supa_pret_3)

        fel1 = layout.findViewById<EditText>(R.id.felul_doi_1)
        pretFel1 = layout.findViewById<EditText>(R.id.felul_doi_pret_1)

        fel2 = layout.findViewById<EditText>(R.id.felul_doi_2)
        pretFel2 = layout.findViewById<EditText>(R.id.felul_doi_pret_2)

        fel3 = layout.findViewById<EditText>(R.id.felul_doi_3)
        pretFel3 = layout.findViewById<EditText>(R.id.felul_doi_pret_3)

        garn1 = layout.findViewById<EditText>(R.id.garnitura_1)
        pretGarn1 = layout.findViewById<EditText>(R.id.garnitura_pret_1)

        garn2 = layout.findViewById<EditText>(R.id.garnitura_2)
        pretGarn2 = layout.findViewById<EditText>(R.id.garnitura_pret_2)

        val button_ok = layout.findViewById<Button>(R.id.yes)
        button_ok.setOnClickListener {
            val Supa1 = sup1!!.text.toString().trim()
            val pretSupa1 = pretSup1!!.text.toString().trim()

            val Supa2 = sup2!!.text.toString().trim()
            val pretSupa2 = pretSup2!!.text.toString().trim()

            val Supa3 = sup3!!.text.toString().trim()
            val pretSupa3 = pretSup3!!.text.toString().trim()

            val Fel1 = fel1!!.text.toString().trim()
            val pretFel1 = pretFel1!!.text.toString().trim()

            val Fel2 = fel2!!.text.toString().trim()
            val pretFel2 = pretFel2!!.text.toString().trim()

            val Fel3 = fel3!!.text.toString().trim()
            val pretFel3 = pretFel3!!.text.toString().trim()

            val Garnit1 = garn1!!.text.toString().trim()
            val pretGarn1 = pretGarn1!!.text.toString().trim()

            val Garnit2 = garn2!!.text.toString().trim()
            val pretGarn2 = pretGarn2!!.text.toString().trim()

            val course1 = Course(0, CourseType.Felul_1, Meal(Supa1, pretSupa1.toFloat()))
            val course2 = Course(1, CourseType.Felul_1, Meal(Supa2, pretSupa2.toFloat()))
            val course3 = Course(2, CourseType.Felul_1, Meal(Supa3, pretSupa3.toFloat()))
            val course4 = Course(3, CourseType.Felul_2, Meal(Fel1, pretFel1.toFloat()))
            val course5 = Course(4, CourseType.Felul_2, Meal(Fel2, pretFel2.toFloat()))
            val course6 = Course(5, CourseType.Felul_2, Meal(Fel3, pretFel3.toFloat()))
            val course7 = Course(6, CourseType.Garnituri, Meal(Garnit1, pretGarn1.toFloat()))
            val course8 = Course(7, CourseType.Garnituri, Meal(Garnit2, pretGarn2.toFloat()))

            val listOfCourse: MutableList<Course> = mutableListOf(course1, course2, course3, course4, course5, course6, course7, course8)

            val menu = Menu(listOfCourse, dateLong)

            database!!.child(dateChild).setValue(menu)

            dialog.dismiss()

            Toast.makeText(this, "Menu was added to database", Toast.LENGTH_LONG).show()

        }


        val button_no = layout.findViewById<Button>(R.id.no)
        button_no.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setView(layout)
        dialog.show()


    }

    private fun updateDelete(){

        val dialog = AlertDialog.Builder(this).create()
        val layout = layoutInflater.inflate(R.layout.admin_item, null)

        editDate = layout.findViewById(R.id.admin_edit_date)
        editDate!!.setText(formatDate(dataFire!!))
        editDate!!.setOnClickListener {

            val date = DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendarDate.set(Calendar.YEAR, year)
                calendarDate.set(Calendar.MONTH, monthOfYear)
                calendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
                val time = dateFormat.format(calendarDate.timeInMillis)

                editDate!!.setText(time.substring(0, time.length-1).toUpperCase())
                dataFire = calendarDate.timeInMillis
                dateChild = dataFire.toString()
                Log.d("Admin Activity", dateChild)
            }

            DatePickerDialog(this@AdminActivity, date, calendarDate.get(Calendar.YEAR),
                    calendarDate.get(Calendar.MONTH), calendarDate.get(Calendar.DAY_OF_MONTH)).show()

        }

        sup1 = layout.findViewById<EditText>(R.id.admin_supa_1)
        sup1!!.setText(soup1)
        pretSup1 = layout.findViewById<EditText>(R.id.admin_supa_pret_1)
        pretSup1!!.setText(pretSupa1.toString())

        sup2 = layout.findViewById<EditText>(R.id.admin_supa_2)
        sup2!!.setText(soup2)
        pretSup2 = layout.findViewById<EditText>(R.id.admin_supa_pret_2)
        pretSup2!!.setText(pretSupa2.toString())

        sup3 = layout.findViewById<EditText>(R.id.admin_supa_3)
        sup3!!.setText(soup3)
        pretSup3 = layout.findViewById<EditText>(R.id.admin_supa_pret_3)
        pretSup3!!.setText(pretSupa3.toString())

        fel1 = layout.findViewById<EditText>(R.id.admin_felul_doi_1)
        fel1!!.setText(main1)
        pretFel1 = layout.findViewById<EditText>(R.id.admin_felul_doi_pret_1)
        pretFel1!!.setText(pretMain1.toString())

        fel2 = layout.findViewById<EditText>(R.id.admin_felul_doi_2)
        fel2!!.setText(main2)
        pretFel2 = layout.findViewById<EditText>(R.id.admin_felul_doi_pret_2)
        pretFel2!!.setText(pretMain2.toString())

        fel3 = layout.findViewById<EditText>(R.id.admin_felul_doi_3)
        fel3!!.setText(main3)
        pretFel3 = layout.findViewById<EditText>(R.id.admin_felul_doi_pret_3)
        pretFel3!!.setText(pretMain3.toString())

        garn1 = layout.findViewById<EditText>(R.id.admin_garnitura_1)
        garn1!!.setText(decor1)
        pretGarn1 = layout.findViewById<EditText>(R.id.admin_garnitura_pret_1)
        pretGarn1!!.setText(pretDecor1.toString())

        garn2 = layout.findViewById<EditText>(R.id.admin_garnitura_2)
        garn2!!.setText(decor2)
        pretGarn2= layout.findViewById<EditText>(R.id.admin_garnitura_pret_2)
        pretGarn2!!.setText(pretDecor2.toString())

        val buttonUpdate = layout.findViewById<Button>(R.id.admin_update)
        val buttonCancel = layout.findViewById<Button>(R.id.admin_cancel)

        buttonUpdate.setOnClickListener {
            val Supa1 = sup1!!.text.toString().trim()
            val pretSupa1 = pretSup1!!.text.toString().trim()

            val Supa2 = sup2!!.text.toString().trim()
            val pretSupa2 = pretSup2!!.text.toString().trim()

            val Supa3 = sup3!!.text.toString().trim()
            val pretSupa3 = pretSup3!!.text.toString().trim()

            val Fel1 = fel1!!.text.toString().trim()
            val pretFel1 = pretFel1!!.text.toString().trim()

            val Fel2 = fel2!!.text.toString().trim()
            val pretFel2 = pretFel2!!.text.toString().trim()

            val Fel3 = fel3!!.text.toString().trim()
            val pretFel3 = pretFel3!!.text.toString().trim()

            val Garnit1 = garn1!!.text.toString().trim()
            val pretGarn1 = pretGarn1!!.text.toString().trim()

            val Garnit2 = garn2!!.text.toString().trim()
            val pretGarn2 = pretGarn2!!.text.toString().trim()

            val course1 = Course(0, CourseType.Felul_1, Meal(Supa1, pretSupa1.toFloat()))
            val course2 = Course(1, CourseType.Felul_1, Meal(Supa2, pretSupa2.toFloat()))
            val course3 = Course(2, CourseType.Felul_1, Meal(Supa3, pretSupa3.toFloat()))
            val course4 = Course(3, CourseType.Felul_2, Meal(Fel1, pretFel1.toFloat()))
            val course5 = Course(4, CourseType.Felul_2, Meal(Fel2, pretFel2.toFloat()))
            val course6 = Course(5, CourseType.Felul_2, Meal(Fel3, pretFel3.toFloat()))
            val course7 = Course(6, CourseType.Garnituri, Meal(Garnit1, pretGarn1.toFloat()))
            val course8 = Course(7, CourseType.Garnituri, Meal(Garnit2, pretGarn2.toFloat()))

            val listOfCourse: MutableList<Course> = mutableListOf(course1, course2, course3, course4, course5, course6, course7, course8)
            val menu = Menu(listOfCourse, dataFire!!)

            database!!.child(key!!).removeValue()
            database!!.child(dateChild).setValue(menu)

            dialog.dismiss()

            Toast.makeText(this, "Menu was updated to database", Toast.LENGTH_LONG).show()
        }

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setView(layout)
        dialog.show()

    }

    private fun formatDate(time: Long): String{

        val dateFormat = SimpleDateFormat("EEEE, d LLL", Locale.forLanguageTag("ro-RO"))
        val text = dateFormat.format(time)
        val date = text.substring(0, text.length-1).toUpperCase()
        return date
    }
}
