package com.example.presentation.arts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.presentation.R
//import com.example.lomakincountriesapp.data.arts.ArtsEntity
import com.example.presentation.databinding.ArtFragmentBinding

class ArtsAdapter() : ListAdapter<com.example.domain.ArtsDomainEntity, ArtsAdapter.ArtsViewHolder>(diffUtil) {

    class ArtsViewHolder(view: View) : ViewHolder(view) {
        private val firstPartOfUrl = "https://www.artic.edu/iiif/2/"
        private val lastPartOfUrl = "/full/843,/0/default.jpg"
        private val bindings by viewBinding(ArtFragmentBinding::bind)
        fun bind(art: com.example.domain.ArtsDomainEntity) {
            bindings.titleText.text = art.title
            bindings.artistText.text = art.artistDisplay
            Glide.with(itemView.context).load(firstPartOfUrl + art.imageUrl + lastPartOfUrl)
                .into(bindings.artImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtsViewHolder {
        val view = LayoutInflater.from(parent.context.applicationContext).inflate(
            R.layout.art_fragment, parent, false
        )

        return ArtsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtsViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<com.example.domain.ArtsDomainEntity>() {
            override fun areItemsTheSame(oldItem: com.example.domain.ArtsDomainEntity, newItem: com.example.domain.ArtsDomainEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: com.example.domain.ArtsDomainEntity, newItem: com.example.domain.ArtsDomainEntity): Boolean {
                return oldItem.currentPage == newItem.currentPage
            }

        }
    }
}