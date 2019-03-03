package com.noemi.android.meniu.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noemi.android.meniu.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setTitle(getString(R.string.name))

        conslay_main.setOnClickListener{
            val intent = Intent(this, LaunchActivity::class.java)
            startActivity(intent)

        }
    }
}
