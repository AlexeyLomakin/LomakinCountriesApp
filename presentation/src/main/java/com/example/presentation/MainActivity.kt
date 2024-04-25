package com.example.presentation


import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
        if (isInternetNotAvailable(this)) {
            val exitDialog = AlertDialog.Builder(this)
            exitDialog.setMessage("У вас нет подключения к интернету")
                .setPositiveButton("Ok"){_, _ ->
                    this.finish()
                }
            exitDialog.show()
        } else {
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

    private fun isInternetNotAvailable(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return true
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return true
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> false
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> false
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> false
            else ->  false
        }
    }
}