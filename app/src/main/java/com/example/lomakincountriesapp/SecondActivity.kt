package com.example.lomakincountriesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity


class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val intent = intent
        val getCountryName = intent.getStringExtra("countryName")
        Toast.makeText(this, getCountryName, Toast.LENGTH_LONG).show()
    }
}
