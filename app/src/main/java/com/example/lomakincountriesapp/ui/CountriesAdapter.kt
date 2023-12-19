package com.example.lomakincountriesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lomakincountriesapp.R
import com.example.lomakincountriesapp.data.Country
import com.example.lomakincountriesapp.databinding.PirateFlagFragmentBinding
import com.example.lomakincountriesapp.databinding.WhiteFlagFragmentBinding

class CountriesAdapter(
    private val countriesList: List<Country>,
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) PIRATE_FLAG else WHITE_FLAG
    }

    class PirateViewHolder(view: View) : ViewHolder(view) {
        private val bindings by viewBinding(PirateFlagFragmentBinding::bind)
        fun bind(country: Country) {
            bindings.countryName.text = country.name?.common
            bindings.flag.setImageResource(R.drawable.pirate_flag)
        }
    }

    class WhiteFlagHolder(view: View) : ViewHolder(view) {
        private val bindings by viewBinding(WhiteFlagFragmentBinding::bind)
        fun bind(country: Country) {
            bindings.countryName.text = country.name?.common
            bindings.flag.setImageResource(R.drawable.flag)
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup, viewType: Int
    ): ViewHolder {

        return if (viewType == PIRATE_FLAG) {
            val view = LayoutInflater.from(viewGroup.context.applicationContext).inflate(
                R.layout.pirate_flag_fragment, viewGroup, false
            )
            PirateViewHolder(view)
        } else {
            val view = LayoutInflater.from(viewGroup.context.applicationContext).inflate(
                R.layout.white_flag_fragment, viewGroup, false
            )
            WhiteFlagHolder(view)
        }
    }

    override fun getItemCount(): Int = countriesList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        return if (getItemViewType(position) == PIRATE_FLAG) {
            (viewHolder as PirateViewHolder).bind(countriesList[position])
        } else {
            (viewHolder as WhiteFlagHolder).bind(countriesList[position])
        }
    }

    companion object {
        const val PIRATE_FLAG = 0
        const val WHITE_FLAG = 1
    }
}
