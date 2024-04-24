package com.example.presentation

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.arts.ArtsFragment
import com.example.presentation.countries.CountriesListFragment
import com.example.presentation.countries.CountriesSearchFragment
import com.example.presentation.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isInternetAvailable(requireContext())) {

            val enterDialog = AlertDialog.Builder(requireActivity())

            enterDialog.setMessage("Добро пожаловать!")
                .setPositiveButton("Ok"){dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton("Пока"){_,_ ->
                    requireActivity().finish()
                }
            enterDialog.show()
        }
        else{
            val exitDialog = AlertDialog.Builder(requireActivity())
            exitDialog.setMessage("У вас нет подключения к интернету")
                .setPositiveButton("Ok"){_, _ ->
                    requireActivity().finish()
                }
            exitDialog.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                val exitDialog = AlertDialog.Builder(requireContext())
                exitDialog.setMessage(R.string.arts_alert_dialog_farewell_message)
                    .setPositiveButton(R.string.arts_alert_dialog_farewell_positive_button){_, _ ->
                        requireActivity().finish()
                    }
                    .setNegativeButton(R.string.arts_alert_dialog_farewell_negative_button){dialog, _ ->
                        dialog.dismiss()
                    }.create().show()
            }.isEnabled = true


            artsListButton.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainerView, ArtsFragment()).commit()
            }

            countriesListButton.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainerView, CountriesListFragment()).commit()
            }

            searchButton.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainerView, CountriesSearchFragment()).commit()
            }
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

            else ->  false
        }
    }
}
