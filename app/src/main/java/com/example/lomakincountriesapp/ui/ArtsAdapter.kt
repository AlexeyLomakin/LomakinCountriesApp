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
import com.example.lomakincountriesapp.databinding.ArtFragmentBinding

class ArtsAdapter() : ListAdapter<Arts, ArtsAdapter.PaginationViewHolder>(diffUtil) {

    class PaginationViewHolder(view: View) : ViewHolder(view) {
        private val firstPartOfUrl = "https://www.artic.edu/iiif/2/"
        private val lastPartOfUrl = "/full/843,/0/default.jpg"
        private val bindings by viewBinding(ArtFragmentBinding::bind)
        fun bind(art: Arts) {
            bindings.titleText.text = art.title
            bindings.artistText.text = art.artist_display
            Glide.with(itemView.context).load(firstPartOfUrl + art.image_id + lastPartOfUrl)
                .into(bindings.artImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginationViewHolder {
        val view = LayoutInflater.from(parent.context.applicationContext).inflate(
            R.layout.art_fragment, parent, false
        )

        return PaginationViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaginationViewHolder, position: Int) =
        holder.bind(getItem(position))

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