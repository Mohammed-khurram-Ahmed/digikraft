package com.mohammed.khurram.digikraft.uiviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohammed.khurram.digikraft.R
import com.mohammed.khurram.digikraft.databinding.ViewBikeStationBinding
import com.mohammed.khurram.digikraft.models.Feature
import com.mohammed.khurram.digikraft.uiviews.ListScreenFragment

/**
 * Adapter class to bind data from data source to recycle view items
 */
class StationAdapter(private val fragment: ListScreenFragment) :
    RecyclerView.Adapter<StationAdapter.StationViewHolder>() {
    var list = emptyList<Feature>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val binding = DataBindingUtil.inflate<ViewBikeStationBinding>(
            LayoutInflater.from(parent.context),
            R.layout.view_bike_station, parent, false
        );
        return StationViewHolder(fragment, binding);
    }


    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class StationViewHolder(
        private val fragment: ListScreenFragment,
        private val binding: ViewBikeStationBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feature: Feature) {
            binding.item = feature.properties
            binding.root.setOnClickListener {
                fragment.navigateToDetails(feature)
            }
        }
    }

    /**
     * Method to load or updates item into the adapter
     */
    fun updateItems(items: List<Feature>?) {
        list = items ?: emptyList()
        notifyDataSetChanged()
    }

}