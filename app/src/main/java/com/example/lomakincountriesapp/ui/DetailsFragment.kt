package com.example.lomakincountriesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.DetailsFragmentBinding

class DetailsFragment: Fragment(R.layout.search_fragment) {

    private val binding by viewBinding(DetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            var capital: Bundle? = null
           /* setFragmentResultListener("requestKey") { _, bundle ->
                capital = bundle.getBundle("bundleKey")
            }
            binding.capitalTextInfo.text = capital?.toString()*/

        }
    }
}