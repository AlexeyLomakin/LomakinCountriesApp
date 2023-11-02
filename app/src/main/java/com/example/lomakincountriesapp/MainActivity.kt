package com.example.lomakincountriesapp

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.Date

class MainActivity : AppCompatActivity() {
    private var startTime: Date = Calendar.getInstance().time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
