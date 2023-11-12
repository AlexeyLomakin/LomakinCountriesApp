package com.example.lomakincountriesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.DetailsFragmentBinding

class DetailsFragment: Fragment(R.layout.details_fragment) {

    private val binding by viewBinding(DetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            requireActivity().supportFragmentManager.setFragmentResultListener("requestKey", this@DetailsFragment) { requestKey, bundle ->
                val  country = bundle.getBundle("bundleKey")
                Log.d("result2","$country")
            }
        }
    }
}