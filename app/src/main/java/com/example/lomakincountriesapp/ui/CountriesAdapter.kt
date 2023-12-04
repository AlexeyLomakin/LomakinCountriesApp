package com.example.lomakincountriesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.data.Country

class CountriesAdapter(
    private val countriesList: List<Country>,
) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    class CountriesViewHolder(view: View) : ViewHolder(view) {

        val textView: TextView = view.findViewById(R.id.text_view)
        val flag: ImageView = view.findViewById(R.id.flag)
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

        viewHolder.textView.text = countriesList[position].name?.common

        Glide.with(viewHolder.itemView.context).load(
            countriesList[position].flags?.png
        ).into(viewHolder.flag)

    }
}