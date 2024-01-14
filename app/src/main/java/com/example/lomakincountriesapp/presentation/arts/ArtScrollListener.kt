package com.example.lomakincountriesapp.presentation.arts

import androidx.recyclerview.widget.RecyclerView

abstract class ArtScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (recyclerView.canScrollVertically(newState)) {
            onPageFinished()
        }
        if (!recyclerView.canScrollVertically(newState)) {
            onLastPage()
        }
    }
}

abstract fun onPageFinished()
abstract fun onLastPage()
}