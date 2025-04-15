package com.wilfredo.poke.module.ui.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wilfredo.poke.module.databinding.FragmentPokemonListBinding
import com.wilfredo.poke.module.di.ModuleViewModel
import com.wilfredo.poke.module.di.ViewModelFactory

class PokemonListFragment: Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PokemonListViewModel> {
        ViewModelFactory(binding.root.context, ModuleViewModel.PokemonList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPokemon()
    }
}