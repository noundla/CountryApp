package com.sv.sampleapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.sv.sampleapp.R
import com.sv.sampleapp.model.CountryItem
import com.sv.sampleapp.util.AppConstants

interface CountrySelectionListener {
    fun onCountrySelected(position: Int)
}

class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private val countryList: List<CountryItem>?
    private val countrySelectionListener: CountrySelectionListener

    constructor(list: List<CountryItem>?, listener: CountrySelectionListener) {
        countryList = list
        countrySelectionListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount(): Int {
        return countryList?.size ?: 0
    }

    private fun getItem(position: Int): CountryItem {
        return countryList!![position]
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countryItem = getItem(position)
        holder.nameTV.text = countryItem.name
        holder.regionTV.text = countryItem.region
        val flagUrl = AppConstants.getFlagUrl(countryItem.alphaCode2 ?: "")
        Picasso.get()
            .load(flagUrl)
            .error(R.drawable.ic_launcher_background)
            .into(holder.flagImageView)
        holder.itemView.setOnClickListener {
            countrySelectionListener.onCountrySelected(holder.layoutPosition)
        }

    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView
        val regionTV: TextView
        val flagImageView: ImageView

        init {
            nameTV = itemView.findViewById(R.id.nameTV)
            regionTV = itemView.findViewById(R.id.regionTV)
            flagImageView = itemView.findViewById(R.id.flagImageView)
        }
    }
}