package com.example.presentation.countries

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.MainFragment
import com.example.presentation.R
import com.example.presentation.databinding.CountriesListFragmentBinding
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.gson.Gson
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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.FragmentContainerView, MainFragment())
                .commit()
        }.isEnabled = true

        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        val retrofitService: CountriesService = retrofit
            .create(CountriesService::class.java)


        CoroutineScope(Dispatchers.IO).launch {

            val countriesListResponse = retrofitService
                .getAllCountries()
            if (countriesListResponse.isSuccessful) {

                val countriesList = countriesListResponse.body()?.sortedBy {country ->
                    country.name?.common
                }
                withContext(Dispatchers.Main) {
                    val countriesListAdapter = countriesList?.let { CountriesAdapter(it) }
                    binding.countriesList.adapter = countriesListAdapter
                    binding.countriesList.layoutManager = LinearLayoutManager(requireContext())
                    binding.countriesList.addItemDecoration(divider)
                    countriesListAdapter?.setOnItemClickListener(object : CountriesAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val country = countriesList[position]
                            val jsonString = Gson().toJson(country)
                            setFragmentResult(
                                EXTRA_COUNTRY_REQUESTED_KEY,
                                bundleOf(COUNTRY_BUNDLE_KEY to jsonString)
                            )

                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.FragmentContainerView, CountriesDetailsFragment()).commit()
                            }
                        })
                }
            }
            else{
                countriesListResponse.message().also{
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                }
                
            }
        }
    }
    companion object {
        private const val EXTRA_COUNTRY_REQUESTED_KEY = "EXTRA_COUNTRY_REQUESTED_KEY"
        private const val COUNTRY_BUNDLE_KEY = "COUNTRY_BUNDLE_KEY"
    }
}
