package com.example.lomakincountriesapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.lomakincountriesapp.R

class CountriesAdapter(context: Context,  private val items: List<String?>): ArrayAdapter<String?>(context, R.layout.country_fragment, items) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val  convertView = LayoutInflater
            .from(context).
            inflate(R.layout.country_fragment,
                parent,
                false)

        val textView = convertView.findViewById<TextView>(R.id.text_view)
        val imageView = convertView.findViewById<ImageView>(R.id.flag)
        textView.text = items[position]

        if (position % 2 == 0)
            imageView.setImageResource(R.drawable.flag)
        else
            imageView.setImageResource(R.drawable.pirate_flag)



        return convertView
    }
}