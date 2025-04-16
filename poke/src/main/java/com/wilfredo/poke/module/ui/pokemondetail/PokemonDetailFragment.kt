package com.wilfredo.poke.module.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.wilfredo.poke.module.R
import com.wilfredo.poke.module.common.Constants.IMAGE_URL
import com.wilfredo.poke.module.databinding.FragmentPokemonDetailBinding
import com.wilfredo.poke.module.di.ModuleViewModel
import com.wilfredo.poke.module.di.ViewModelFactory
import com.wilfredo.poke.module.utils.toLowercase
import kotlinx.coroutines.launch


class PokemonDetailFragment : Fragment() {

    private val viewModel by viewModels<PokemonDetailViewModel> {
        ViewModelFactory(binding.root.context, ModuleViewModel.PokemonDetail)
    }

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
        initUiState()
        arguments?.getInt(ARG_POKEMON_ID)?.let { viewModel.getPokemonDetail(it) }
    }

    private fun initElements() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.fabToggleFavorite.setOnClickListener { viewModel.toggleFavorite() }
    }

    private fun initUiState() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { pokemon ->
                toolbar.title = pokemon.name.toLowercase()
                val number = if (pokemon.url.endsWith("/")) {
                    pokemon.url.dropLast(1).takeLastWhile {
                        it.isDigit()
                    }

                } else {
                    pokemon.url.takeLastWhile { it.isDigit() }
                }
                val url =
                    "$IMAGE_URL$number.png"
                Glide.with(binding.root.context)
                    .load(url)
                    .into(ivPokemonFront)

                pokemonName.text = pokemon.name.toLowercase()
                pokemonHeight.text = getString(R.string.height_value_text, pokemon.height.toString())
                pokemonWeight.text = getString(R.string.weight_value_text, pokemon.weight.toString())
                val favIcon = if (pokemon.isFavorite) R.drawable.ic_favorite_fill
                else R.drawable.ic_favorite
                fabToggleFavorite.setImageResource(favIcon)
            }
        }
    }

    companion object {
        private const val ARG_POKEMON_ID = "arg_pokemon_id"

        fun getBundle(pokemonId: Int) = bundleOf(ARG_POKEMON_ID to pokemonId)
    }
}