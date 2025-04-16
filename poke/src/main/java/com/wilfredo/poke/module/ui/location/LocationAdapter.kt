package com.wilfredo.poke.module.ui.location

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wilfredo.poke.module.R
import com.wilfredo.poke.module.databinding.ItemLocationBinding
import com.wilfredo.poke.module.domain.model.Location
import com.wilfredo.poke.module.utils.inflate
import java.text.SimpleDateFormat
import java.util.Locale

class LocationAdapter() :
    ListAdapter<Location, LocationAdapter.LocationViewHolder>(
        object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
                oldItem == newItem

        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return parent.inflate(R.layout.item_location, false)
            .let { view -> ItemLocationBinding.bind(view) }
            .let { binding -> LocationViewHolder(binding) }
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val Location = getItem(position)
        holder.bind(Location)
    }

    class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Location) = with(binding) {
            val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val formattedDate = formatter.format(item.timestamp)
            ("Lat: " + item.latitude.toString() + " Long: " + item.longitude.toString() + ", Hora: " + formattedDate).also { tvLocation.text = it }
        }
    }
}