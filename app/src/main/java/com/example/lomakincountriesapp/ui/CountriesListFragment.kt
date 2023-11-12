package com.example.lomakincountriesapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.MainActivity
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.CountriesListFragmentBinding
import com.example.lomakincountriesapp.network.CountriesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesListFragment: Fragment(R.layout.countries_list_fragment) {

    private val baseUrl = "https://restcountries.com/v3.1/"
    private val binding by viewBinding(CountriesListFragmentBinding::bind)



    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            var listOfCountries: ArrayList<String> = ArrayList()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
            val retrofitService: CountriesService by lazy {
                retrofit.create(CountriesService::class.java)
            }
            CoroutineScope(Dispatchers.Main).launch {
                    listOfCountries.add(
                        retrofitService.getAllCountries()
                            .map {
                                it.name
                            }.toString()
                    )
                Log.d("list","$listOfCountries")

                listOfCountries.remove("]")
                listOfCountries.remove("[")
                Log.d("list2","$listOfCountries")

            }.start()
            val context = context as MainActivity
            val adapter = ArrayAdapter(context,R.id.countries_list, listOfCountries)
            countriesList.adapter = adapter
        }
    }
}