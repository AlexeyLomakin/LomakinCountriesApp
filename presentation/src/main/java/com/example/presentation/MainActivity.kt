package com.example.presentation


import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val enterDialog = AlertDialog.Builder(this)

        enterDialog.setMessage("Добро пожаловать!")
            .setPositiveButton("Ok"){dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Пока"){_,_ ->
                this.finish()
            }
        enterDialog.show()
    }
}