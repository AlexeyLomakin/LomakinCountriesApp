package com.example.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.arts.ArtsFragment
import com.example.presentation.countries.CountriesListFragment
import com.example.presentation.countries.CountriesSearchFragment
import com.example.presentation.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            artsListButton.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainerView, ArtsFragment()).commit()
            }

            countriesListButton.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainerView, CountriesListFragment()).commit()
            }

            searchButton.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainerView, CountriesSearchFragment()).commit()
            }
        }
    }
}