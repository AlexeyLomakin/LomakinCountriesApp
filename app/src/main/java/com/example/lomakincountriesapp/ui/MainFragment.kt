package com.example.lomakincountriesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
           countriesListButton.setOnClickListener {
               requireActivity().supportFragmentManager.beginTransaction().replace(
                   R.id.FragmentContainerView,
                   CountriesListFragment()
               ).commit()
            }
            searchButton.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.FragmentContainerView,
                    SearchFragment()
                ).commit()
            }
        }
    }
}