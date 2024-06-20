package com.example.presentation.countries

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesDetailsFragment : Fragment(R.layout.details_fragment) {

    private val binding by viewBinding(DetailsFragmentBinding::bind)
    private val viewModel: CountriesDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.FragmentContainerView, CountriesListFragment())
                .commit()
        }.isEnabled = true

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val countryName = bundle.getString("countryName")
            countryName?.let {
                viewModel.loadCountryByName(it)
            }
        }

        viewModel.country.observe(viewLifecycleOwner) { country ->
            country?.let {
                binding.countryName.text = it.name
                binding.capitalText.text = it.capital
                binding.populationText.text= it.population.toString()
                Glide.with(this).load(it.flags).into(binding.flag)
            }
        }
    }
}


