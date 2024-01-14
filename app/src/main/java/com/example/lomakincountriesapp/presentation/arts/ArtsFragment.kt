package com.example.lomakincountriesapp.presentation.arts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.ArtsListFragmentBinding
import com.example.lomakincountriesapp.di.arts.DaggerArtsComponent
import com.example.lomakincountriesapp.presentation.arts.artsviewmodels.ArtsViewModel
import com.example.lomakincountriesapp.presentation.arts.artsviewmodels.ArtsViewModelFactory
import com.google.android.material.divider.MaterialDividerItemDecoration
import javax.inject.Inject

class ArtsFragment : Fragment(R.layout.arts_list_fragment) {

    @Inject lateinit var factory: ArtsViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            factory
        )[ArtsViewModel::class.java]
    }

    private val binding by viewBinding(ArtsListFragmentBinding::bind)
    private var adapter = ArtsAdapter()

    inner class ArtsScrollListener : ArtScrollListener() {
//        override fun loadMoreItems() {
//            viewModel.loadMoreItems()
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerArtsComponent
            .builder()
            .build()
            .inject(this)

        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        binding.artList.layoutManager = LinearLayoutManager(requireContext())
        binding.artList.addItemDecoration(divider)
        binding.artList.adapter = adapter
        binding.artList.addOnScrollListener(ArtsScrollListener())

        viewModel.artsData.observe(viewLifecycleOwner) { arts ->
            adapter.submitList(arts)
        }
    }
}

