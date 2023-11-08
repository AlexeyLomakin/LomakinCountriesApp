package com.example.lomakincountriesapp.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.SearchFragmentBinding
import com.example.lomakincountriesapp.network.CountriesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchFragment: Fragment(R.layout.search_fragment) {


    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val baseUrl = "https://restcountries.com/v3.1/"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
            val retrofitService: CountriesService by lazy {
                retrofit.create(CountriesService::class.java)
            }

            var capital: String? = null
            searchButton.setOnClickListener {

                CoroutineScope(Dispatchers.Main).launch {
                    capital =
                        retrofitService.getCountryByName(binding.countryTextSearch.text.toString())
                            .map {
                                it.capital
                            }.toString()
                    capital = capital?.replace("[", "")?.replace("]", "")
                    setFragmentResult("requestKey", bundleOf("bundleKey" to capital))
                }
                Toast.makeText(activity, "$capital", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.FragmentContainerView,
                    DetailsFragment()
                ).commit()
            }
        }
    }
}