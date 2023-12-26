package com.example.lomakincountriesapp.ui

import androidx.recyclerview.widget.RecyclerView

abstract class ArtScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (recyclerView.canScrollVertically(newState)) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()
}