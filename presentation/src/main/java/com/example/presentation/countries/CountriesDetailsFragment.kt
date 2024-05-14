package com.example.presentation.countries

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.DetailsFragmentBinding

class CountriesDetailsFragment : Fragment(R.layout.details_fragment) {

    private val binding by viewBinding(DetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.FragmentContainerView, CountriesListFragment()).commit()
            }.isEnabled = true

        }
    }


}

