package com.example.lomakincountriesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.data.Country
import com.example.lomakincountriesapp.databinding.CountryFragmentBinding

class CountriesAdapter(
    private val countriesList: List<Country>,
) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    class CountriesViewHolder(view: View) : ViewHolder(view) {
        val bindings by viewBinding(CountryFragmentBinding::bind)
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup, viewType: Int
    ): CountriesViewHolder {

        val view = LayoutInflater.from(viewGroup.context.applicationContext).inflate(
            R.layout.country_fragment, viewGroup, false
        )

        return CountriesViewHolder(view)
    }

    override fun getItemCount(): Int = countriesList.size

    override fun onBindViewHolder(viewHolder: CountriesViewHolder, position: Int) {

        viewHolder.bindings.textView.text = countriesList[position].name?.common

        Glide.with(viewHolder.itemView.context).load(
            countriesList[position].flags?.png
        ).into(viewHolder.bindings.flag)

    }
}