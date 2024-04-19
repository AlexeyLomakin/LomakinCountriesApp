package com.example.presentation.countries

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.countries.dataclasses.Country
import com.example.presentation.databinding.DetailsFragmentBinding
import com.google.gson.Gson

class CountriesDetailsFragment : Fragment(R.layout.details_fragment) {

    private val binding by viewBinding(DetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.FragmentContainerView, CountriesSearchFragment()).commit()
            }.isEnabled = true

            requireActivity().supportFragmentManager.setFragmentResultListener(
                EXTRA_COUNTRY_REQUESTED_KEY, this@CountriesDetailsFragment
            ) { _, bundle ->
                val countryStr = bundle.getString(COUNTRY_BUNDLE_KEY)

                val country = Gson().fromJson(
                    countryStr, Country::class.java
                )

                capitalTextInfo.text = country.capital.first()

                areaTextInfo.text = country.area.toString()

                populationTextInfo.text = country.population.toString()

                countryName.text = country.name?.official.toString()

                languagesTextInfo.text = country.languages.values.joinToString(",")

                activity?.let {
                    Glide.with(it).load(country.flags?.png).into(flag)
                }
            }
        }
    }

    companion object {
        private const val EXTRA_COUNTRY_REQUESTED_KEY = "EXTRA_COUNTRY_REQUESTED_KEY"
        private const val COUNTRY_BUNDLE_KEY = "COUNTRY_BUNDLE_KEY"
    }
}

