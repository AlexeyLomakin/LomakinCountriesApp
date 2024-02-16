package com.example.presentation.arts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.ArtsListFragmentBinding
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtsFragment : Fragment(R.layout.arts_list_fragment) {

    private val viewModel: ArtsViewModel by viewModels()

    private val binding by viewBinding(ArtsListFragmentBinding::bind)
    private var adapter = ArtsAdapter()

    inner class ArtsScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (recyclerView.canScrollVertically(newState)) {
                viewModel.onPageFinished()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        binding.artList.layoutManager = LinearLayoutManager(requireContext())
        binding.artList.addItemDecoration(divider)
        binding.artList.adapter = adapter
        binding.artList.addOnScrollListener(ArtsScrollListener())

        viewModel.artsData.observe(viewLifecycleOwner) { arts ->
            adapter.submitList(arts)
        }
        viewModel.isMaxArts.observe(viewLifecycleOwner) { maxArts ->
            if (maxArts) {
                Toast.makeText(requireContext(), "The arts are over", Toast.LENGTH_LONG).show()
            }
        }
    }
}


