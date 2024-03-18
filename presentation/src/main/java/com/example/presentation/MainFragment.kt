package com.example.presentation

import android.app.AlertDialog
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

            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            builder.setTitle(R.string.arts_alert_dialog_title)
                .setMessage(R.string.arts_alert_dialog_message)
                .setPositiveButton(R.string.arts_alert_dialog_positive_button) { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.arts_alert_dialog_negative_button) { _, _ ->
                    requireActivity().finish()
                }.create().show()

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