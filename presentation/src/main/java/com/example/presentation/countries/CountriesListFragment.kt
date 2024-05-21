package com.example.presentation.countries

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.MainFragment
import com.example.presentation.R
import com.example.presentation.databinding.CountriesListFragmentBinding
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountriesListFragment : Fragment(R.layout.countries_list_fragment) {

    private val binding by viewBinding(CountriesListFragmentBinding::bind)
    private val viewModel: CountriesViewModel by viewModels()
    private val adapter = CountriesListAdapter()

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
        
        viewModel.countriesData.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                adapter.submitData(pagingData)
            }
        }

        adapter.setOnItemClickListener(object : CountriesListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val country = adapter.snapshot()[position]
                if (country != null) {
                    val jsonString = Gson().toJson(country)
                    setFragmentResult(
                        EXTRA_COUNTRY_REQUESTED_KEY,
                        bundleOf(COUNTRY_BUNDLE_KEY to jsonString)
                    )
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.FragmentContainerView, CountriesDetailsFragment()).commit()
                }
            }
        })

        binding.countriesList.addOnScrollListener(ArtsScrollListener())
        viewModel.isMaxCountries.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "The countries are over", Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class ArtsScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1)) {
                viewModel.onPageFinished()
            }
        }
    }

    companion object {
        private const val EXTRA_COUNTRY_REQUESTED_KEY = "EXTRA_COUNTRY_REQUESTED_KEY"
        private const val COUNTRY_BUNDLE_KEY = "COUNTRY_BUNDLE_KEY"
    }
}


