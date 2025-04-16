package com.wilfredo.poke.module.ui.pokemonlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wilfredo.poke.module.R
import com.wilfredo.poke.module.common.Constants.IMAGE_URL
import com.wilfredo.poke.module.databinding.ItemPokemonBinding
import com.wilfredo.poke.module.domain.model.Pokemon
import com.wilfredo.poke.module.utils.inflate
import com.wilfredo.poke.module.utils.toLowercase

class PokemonAdapter(
    private val listener: (Pokemon) -> Unit,
    private val onButtonClick: (Pokemon) -> Unit,
    private val onImageClick: (Pokemon) -> Unit,
) :
    ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(
        object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem

            override fun getChangePayload(oldItem: Pokemon, newItem: Pokemon): Any? {
                if (oldItem.isFavorite != newItem.isFavorite) return newItem.isFavorite
                return null
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return parent.inflate(R.layout.item_pokemon, false)
            .let { view -> ItemPokemonBinding.bind(view) }
            .let { binding -> PokemonViewHolder(binding) }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon, onButtonClick, onImageClick)
        holder.itemView.setOnClickListener { listener(pokemon) }
    }

    override fun onBindViewHolder(
        holder: PokemonViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        val payload = payloads[0]
        if (payload is Boolean) holder.setIsFavorite(payload)
    }

    class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pokemon, onButtonClick: (Pokemon) -> Unit, onImageClick: (Pokemon) -> Unit
        ) =
            with(binding) {
                val number = if (item.url.endsWith("/")) {
                    item.url.dropLast(1).takeLastWhile {
                        it.isDigit()
                    }

                } else {
                    item.url.takeLastWhile { it.isDigit() }
                }
                val url =
                    "$IMAGE_URL$number.png"
                Glide.with(binding.root.context)
                    .load(url)
                    .into(pokemonImage)
                pokemonName.text = item.name.toLowercase()
                setIsFavorite(item.isFavorite)
                imgFavorite.setOnClickListener {
                    onButtonClick(item)
                    setIsFavorite(!item.isFavorite)
                    item.isFavorite = !item.isFavorite
                }
                pokemonImage.setOnClickListener {
                   onImageClick(item)
                }
            }

        fun setIsFavorite(isFavorite: Boolean) {
            val favIcon = if (isFavorite) R.drawable.ic_favorite_fill
            else R.drawable.ic_favorite
            binding.imgFavorite.setImageResource(favIcon)
        }
    }
}