package com.example.lomakincountriesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.data.Arts
import com.example.lomakincountriesapp.databinding.PageFragmentBinding

class PaginationAdapter(
    private var pagesList: MutableList<Arts> = mutableListOf(),
) : ListAdapter<Arts, PaginationAdapter.PaginationViewHolder>(diffUtil) {

    class PaginationViewHolder(view: View) : ViewHolder(view) {
        private val firstPartOfUrl = "https://www.artic.edu/iiif/2/"
        private val lastPartOfUrl = "/full/843,/0/default.jpg"
        private val bindings by viewBinding(PageFragmentBinding::bind)
        fun bind(page: Arts) {
            bindings.titleText.text = page.title
            bindings.artistText.text = page.artist_display
            Glide.with(itemView.context).load(firstPartOfUrl + page.image_id + lastPartOfUrl)
                .into(bindings.artImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PaginationViewHolder {
        val view = LayoutInflater.from(parent.context.applicationContext).inflate(
            R.layout.page_fragment, parent, false
        )

        return PaginationViewHolder(view)
    }

    override fun getItemCount(): Int = pagesList.size

    override fun onBindViewHolder(holder: PaginationViewHolder, position: Int) =
        holder.bind(pagesList[position])

    fun setData(newData: MutableList<Arts>) {
        newData.forEach {
            pagesList.add(it)
            notifyItemInserted(pagesList.size - 1)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Arts>() {
            override fun areItemsTheSame(oldItem: Arts, newItem: Arts): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Arts, newItem: Arts): Boolean {
                return oldItem == newItem
            }

        }
    }
}