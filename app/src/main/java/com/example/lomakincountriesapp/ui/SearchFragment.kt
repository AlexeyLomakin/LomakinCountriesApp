package com.example.lomakincountriesapp.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.SearchFragmentBinding
import com.example.lomakincountriesapp.network.CountriesService
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchFragment : Fragment(R.layout.search_fragment) {


    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val baseUrl = "https://restcountries.com/v3.1/"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory
                        .create()
                )
                .baseUrl(baseUrl)
                .build()
            val retrofitService: CountriesService = retrofit
                .create(CountriesService::class.java)

            searchButton.setOnClickListener {

                val countryName = binding.countryTextSearch.text.toString()

                CoroutineScope(Dispatchers.IO)
                    .launch {
                        val country = retrofitService
                            .getCountryByName(countryName)
                            .first()

                        val jsonString = Gson()
                            .toJson(country)

                        withContext(Dispatchers.Main) {
                            setFragmentResult(
                                EXTRA_COUNTRY_REQUESTED_KEY,
                                bundleOf(COUNTRY_BUNDLE_KEY to jsonString)
                            )
                        }

                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.FragmentContainerView,
                                DetailsFragment()
                            )
                            .commit()
                    }
            }
        }
    }

    companion object {
        private const val EXTRA_COUNTRY_REQUESTED_KEY = "EXTRA_COUNTRY_REQUESTED_KEY"
        private const val COUNTRY_BUNDLE_KEY = "COUNTRY_BUNDLE_KEY"
    }
}