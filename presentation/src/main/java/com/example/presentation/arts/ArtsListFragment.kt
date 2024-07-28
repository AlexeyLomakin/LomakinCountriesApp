package com.example.presentation.arts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.MainFragment
import com.example.presentation.R
import com.example.presentation.databinding.ArtsListFragmentBinding
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtsListFragment : Fragment(R.layout.arts_list_fragment) {

    private val binding by viewBinding(ArtsListFragmentBinding::bind)
    private val viewModel: ArtsViewModel by viewModels()
    private val adapter = ArtsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.FragmentContainerView, MainFragment())
                .commit()
        }.isEnabled = true

        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)

        binding.artList.layoutManager = LinearLayoutManager(requireContext())
        binding.artList.adapter = adapter
        binding.artList.addItemDecoration(divider)

        viewModel.artsData.observe(viewLifecycleOwner) { artsData ->
            adapter.submitList(artsData)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        binding.artList.addOnScrollListener(ArtsScrollListener())

        viewModel.isMaxArts.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "The arts are over", Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class ArtsScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1)) {
                viewModel.onPageFinished()
            }
        }
    }
}
