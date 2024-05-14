package com.example.presentation.countries

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.MainFragment
import com.example.presentation.R
import com.example.presentation.databinding.CountriesListFragmentBinding
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesListFragment : Fragment(R.layout.countries_list_fragment) {

    private val binding by viewBinding(CountriesListFragmentBinding::bind)
    private val viewModel: CountriesViewModel by viewModels()
    private var adapter = CountriesListAdapter()
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

        binding.countriesList.layoutManager = LinearLayoutManager(requireContext())
        binding.countriesList.adapter = adapter
        binding.countriesList.addItemDecoration(divider)

        viewModel.countriesData.observe(viewLifecycleOwner) {countries ->
            adapter.submitList(countries)
            adapter.setOnItemClickListener(object : CountriesListAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val country = countries[position]
                    val jsonString = Gson().toJson(country)
                    setFragmentResult(
                        EXTRA_COUNTRY_REQUESTED_KEY,
                        bundleOf(COUNTRY_BUNDLE_KEY to jsonString)
                    )
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.FragmentContainerView, CountriesDetailsFragment()).commit()
                    }
                }
            )
        }
    }
    companion object {
        private const val EXTRA_COUNTRY_REQUESTED_KEY = "EXTRA_COUNTRY_REQUESTED_KEY"
        private const val COUNTRY_BUNDLE_KEY = "COUNTRY_BUNDLE_KEY"
    }
}

