package com.example.presentation.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.domain.countries.CountriesDomainEntity
import com.example.presentation.R
import com.example.presentation.databinding.CountryFragmentBinding

class CountriesListAdapter : PagingDataAdapter<CountriesDomainEntity, CountriesListAdapter.CountriesListViewHolder>(diffUtil) {

    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    class CountriesListViewHolder(view: View, listener: OnItemClickListener) : ViewHolder(view) {
        private val bindings by viewBinding(CountryFragmentBinding::bind)

        init {
            view.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }

        fun bind(country: CountriesDomainEntity) {
            bindings.countryName.text = country.name
            Glide.with(itemView.context).load(country.flags).into(bindings.flag)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CountriesListViewHolder {
        val view = LayoutInflater.from(viewGroup.context.applicationContext).inflate(
            R.layout.country_fragment, viewGroup, false
        )
        return CountriesListViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(viewHolder: CountriesListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            viewHolder.bind(item)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CountriesDomainEntity>() {
            override fun areItemsTheSame(oldItem: CountriesDomainEntity, newItem: CountriesDomainEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CountriesDomainEntity, newItem: CountriesDomainEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}