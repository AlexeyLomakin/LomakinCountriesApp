package com.example.lomakincountriesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.data.Country
import com.example.lomakincountriesapp.databinding.DetailsFragmentBinding
import com.google.gson.Gson

class DetailsFragment: Fragment(R.layout.details_fragment) {

    private val binding by viewBinding(DetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            requireActivity()
                .supportFragmentManager
                .setFragmentResultListener(
                "requestKey",
                this@DetailsFragment
            ) { _, bundle ->
                val  countryStr = bundle
                    .getString("bundleKey")
                val country = Gson().fromJson(countryStr
                    , Country::class.java)
                binding.capitalTextInfo.text = country.capital
                    .first()
                binding.areaTextInfo.text = country.area
                    .toLong()
                    .toString()
                binding.populationTextInfo.text = country.population
                    .toLong()
                    .toString()
                binding.countryName.text = country.name?.official
                    .toString()
                binding.languagesTextInfo.text = country.languages.values
                    .joinToString(",")
                activity?.let {
                    Glide.with(it).
                    load(country.flags?.png)
                        .into(binding.flag)
                }
            }
        }
    }
}