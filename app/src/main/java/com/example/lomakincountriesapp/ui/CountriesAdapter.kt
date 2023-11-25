package com.example.lomakincountriesapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.lomakincountriesapp.R

class CountriesAdapter(private val itemsNames: List<String?>,
                       private val itemsFlags: List<String?>,
                       private var context: Context
):
    RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

class CountriesViewHolder(view: View) : ViewHolder(view) {
    val textView: TextView
    val flag: ImageView

    init {
        textView = view.findViewById(R.id.text_view)
        flag = view.findViewById(R.id.flag)
    }
}


    override fun onCreateViewHolder(viewGroup: ViewGroup,
                                    viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.country_fragment,viewGroup,false)

        return CountriesViewHolder(view)
    }
    
    override fun getItemCount(): Int = itemsNames.size

    override fun onBindViewHolder(viewHolder: CountriesViewHolder, position: Int) {
        val country = itemsNames[position]
        val flag = itemsFlags[position]

        with(viewHolder.bindingAdapter) {
            viewHolder.textView.text = country
                Glide.with(context)
                    .load(flag)
                    .into(viewHolder.flag)
        }
    }



}