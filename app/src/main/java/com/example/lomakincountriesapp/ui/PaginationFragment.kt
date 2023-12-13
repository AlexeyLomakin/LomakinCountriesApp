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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaginationFragment : Fragment(R.layout.pagination_list_fragment) {

    private val binding by viewBinding(PaginationListFragmentBinding::bind)
    private val baseUrl = "https://api.artic.edu/api/v1/"
    var adapter = PaginationAdapter()
    private var isLoadingPages = false
    inner class PagesScrollListener(context: Context): PaginationScrollListener(
      LinearLayoutManager(context)
    ) {

        override fun loadMoreItems() {

            val retrofit =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()

            val retrofitService: PaginationService = retrofit
                .create(PaginationService::class.java)

            CoroutineScope(Dispatchers.IO).launch {

            val currentPage = retrofitService.getPageById(null).data

            adapter.pagesList += currentPage


            }
        }

        override fun isLastPage(): Boolean = adapter.pagesList.size == adapter.itemCount


        override fun isLoading(): Boolean = isLoadingPages

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isLoadingPages = true

        PagesScrollListener(requireContext())

        binding.paginationList.layoutManager = LinearLayoutManager(requireContext())
    }
}