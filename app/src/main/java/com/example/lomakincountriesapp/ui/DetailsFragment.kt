package com.example.lomakincountriesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.data.Country
import com.example.lomakincountriesapp.databinding.DetailsFragmentBinding
import com.google.gson.Gson

class DetailsFragment: Fragment(R.layout.details_fragment) {

    private val binding by viewBinding(DetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            requireActivity().
            supportFragmentManager.
            setFragmentResultListener(
                "requestKey",
                this@DetailsFragment
            ) { requestKey, bundle ->
                val  country = bundle.getString("bundleKey")
                val test = Gson().fromJson(country, Country::class.java)
                val capital = test.capital.toString()

                Log.d("name", capital)
            }
        }
    }
}