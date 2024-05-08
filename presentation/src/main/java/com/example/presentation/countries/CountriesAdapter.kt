package com.example.presentation.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.countries.dataclasses.Country
import com.example.presentation.databinding.CountryFragmentBinding

class CountriesAdapter(
    private val countriesList: List<Country>,
) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    class CountriesViewHolder(view: View, listener: OnItemClickListener) : ViewHolder(view) {
        private val bindings by viewBinding(CountryFragmentBinding::bind)
        init {
            view.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
        fun bind(country: Country) {
            bindings.countryName.text = country.name?.common
            Glide.with(itemView.context).load(country.flags?.png).into(bindings.flag)
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup, viewType: Int
    ): CountriesViewHolder {

        val view = LayoutInflater.from(viewGroup.context.applicationContext).inflate(
            R.layout.country_fragment, viewGroup, false
        )

        return CountriesViewHolder(view,onItemClickListener)
    }

    override fun getItemCount(): Int = countriesList.size

    override fun onBindViewHolder(viewHolder: CountriesViewHolder, position: Int) =
        viewHolder.bind(countriesList[position])
}