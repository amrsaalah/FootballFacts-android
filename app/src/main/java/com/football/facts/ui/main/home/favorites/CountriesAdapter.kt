package com.football.facts.ui.main.home.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import com.football.facts.databinding.ListItemCountryBinding
import com.football.facts.ui.main.home.countries.CountryDisplayItem

class CountriesAdapter(
    private val viewModel: FavoritesViewModel
) : RecyclerView.Adapter<CountriesAdapter.CountryHolder>() {

    var countries : ArrayList<CountryDisplayItem> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class CountryHolder(
        private val binding : ListItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(countryDisplayItem: CountryDisplayItem){
            val context = binding.root.context
            binding.txt.text = countryDisplayItem.name
            binding.img.load(countryDisplayItem.icon) {
                decoder(SvgDecoder(context))
            }

            binding.root.setOnClickListener {
                viewModel.onItemClicked(countryDisplayItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(
            ListItemCountryBinding.inflate(
                LayoutInflater.from(parent.context) , parent , false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }


    fun notifyCountryChanged(countryDisplayItem: CountryDisplayItem){
        val index = countries.indexOfFirst {
            it.icon == countryDisplayItem.icon
        }

        if(index >= 0){
            countries[index] = countryDisplayItem
            notifyItemChanged(index)
        }
    }
}