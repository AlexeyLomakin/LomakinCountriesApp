package com.example.lomakincountriesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lomakincountriesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //коммит в новую ветку

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
