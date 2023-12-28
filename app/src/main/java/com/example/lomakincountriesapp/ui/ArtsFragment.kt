package com.example.lomakincountriesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.ArtsListFragmentBinding
import com.example.lomakincountriesapp.network.ArtsService
import com.example.lomakincountriesapp.ui.viewmodels.ArtsApp
import com.example.lomakincountriesapp.ui.viewmodels.ArtsViewModel
import com.example.lomakincountriesapp.ui.viewmodels.ArtsViewModelFactory
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ArtsFragment : Fragment(R.layout.arts_list_fragment) {

    @Inject
    lateinit var retrofitService: ArtsService

    private val binding by viewBinding(ArtsListFragmentBinding::bind)
    private var adapter = ArtsAdapter()

    private val viewModel by lazy {
        ViewModelProvider(this, ArtsViewModelFactory())[ArtsViewModel::class.java]
    }

    inner class ArtsScrollListener : ArtScrollListener() {
        override fun loadMoreItems() {
            viewModel.artsData.observe(viewLifecycleOwner) { arts ->
                adapter.submitList(adapter.currentList + arts)
            }
        }
    }

    inner class ArtsScrollListener: ArtScrollListener(){
        override fun loadMoreItems() {
            viewModel.loadMoreItems()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().application as ArtsApp).appComponent.inject(this)

        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        binding.artList.addItemDecoration(divider)
        binding.artList.adapter = adapter
        viewModel.artsData.observe(viewLifecycleOwner) { arts ->
            adapter.submitList(arts)
        }
        binding.artList.layoutManager = LinearLayoutManager(requireContext())
        binding.artList.addOnScrollListener(ArtsScrollListener)
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}

