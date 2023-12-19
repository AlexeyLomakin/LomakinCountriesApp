package com.example.lomakincountriesapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.databinding.PaginationListFragmentBinding
import com.example.lomakincountriesapp.network.PaginationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaginationFragment : Fragment(R.layout.pagination_list_fragment) {

    private val binding by viewBinding(PaginationListFragmentBinding::bind)
    private val baseUrl = "https://api.artic.edu/api/v1/"
    private var adapter = PaginationAdapter()
    private val retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    private val retrofitService: PaginationService = retrofit
        .create(PaginationService::class.java)

    private var currentPage = 1

    inner class PagesScrollListener(context: Context) : PaginationScrollListener(
        LinearLayoutManager(context)
    ) {
        override fun loadMoreItems() {
            loadContent(++currentPage)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.paginationList.adapter = adapter
        binding.paginationList.layoutManager = LinearLayoutManager(requireContext())
        binding.paginationList.addOnScrollListener(PagesScrollListener(requireContext()))
        loadContent(page = 1)
    }

    private fun loadContent(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = retrofitService.getPageById(page).data
            withContext(Dispatchers.Main) {
                adapter.setData(data)
            }
        }
    }
}