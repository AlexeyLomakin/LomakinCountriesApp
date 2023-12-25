package com.example.lomakincountriesapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.ArtsListFragmentBinding
import com.example.lomakincountriesapp.network.ArtsService
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtsFragment : Fragment(R.layout.arts_list_fragment) {

    private val binding by viewBinding(ArtsListFragmentBinding::bind)
    private val baseUrl = "https://api.artic.edu/api/v1/"
    private var adapter = ArtsAdapter()
    private val retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    private val retrofitService: ArtsService = retrofit
        .create(ArtsService::class.java)
    private var currentPage = 1

    inner class PagesScrollListener(context: Context) : ArtScrollListener(
        LinearLayoutManager(context)
    ) {
        override fun loadMoreItems() {
            loadContent(++currentPage)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadContent(1)
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.artList.adapter = adapter
        binding.artList.layoutManager = LinearLayoutManager(requireContext())
        binding.artList.addOnScrollListener(PagesScrollListener(requireContext()))
        binding.artList.addItemDecoration(divider)
    }

    private fun loadContent(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val arts = retrofitService.getArtByPage(page)
            withContext(Dispatchers.Main) {
                adapter.setData(arts.data)
//                adapter.submitList(arts.data)
            }
        }
    }
}