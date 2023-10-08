package com.example.lomakincountriesapp

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Date

class MainActivity : AppCompatActivity() {

    private var startTime: Date = Calendar.getInstance().time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryName:TextView = findViewById(R.id.country_name_edit_text)
        val button:Button = findViewById(R.id.confirm_button)
        button.setOnClickListener{
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            val getCountryName = countryName.text.toString()
            intent.putExtra("countryName", getCountryName)
            startActivity(intent)
        }
    }
    override fun onStop() {
        super.onStop()
        startTime  = Calendar.getInstance().time
    }
    override fun onRestart() {
        super.onRestart()
        val endTime = Calendar.getInstance().time
        Toast.makeText(this, "Время неактивности приложения: ${endTime.time.minus(startTime.time)/1000}с", Toast.LENGTH_LONG).show()
    }
}
