package com.example.lomakincountriesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.CountriesListFragmentBinding
import com.example.lomakincountriesapp.network.CountriesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesListFragment : Fragment(R.layout.countries_list_fragment) {

    private val baseUrl = "https://restcountries.com/v3.1/"
    private val binding by viewBinding(CountriesListFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
                .build()

        val retrofitService: CountriesService = retrofit
            .create(CountriesService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val countriesList = retrofitService
                .getAllCountries()
                .sortedBy { it.name?.common }

            withContext(Dispatchers.Main) {

                val adapter = CountriesAdapter(
                    countriesList
                )

                binding.countriesList.layoutManager = LinearLayoutManager(requireContext())
                binding.countriesList.adapter = adapter
            }
        }
    }
}