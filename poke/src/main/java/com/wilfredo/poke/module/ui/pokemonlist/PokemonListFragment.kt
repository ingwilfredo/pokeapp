package com.wilfredo.poke.module.ui.pokemonlist

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.wilfredo.poke.module.R
import com.wilfredo.poke.module.common.Constants.IMAGE_URL
import com.wilfredo.poke.module.databinding.FragmentPokemonListBinding
import com.wilfredo.poke.module.di.ModuleViewModel
import com.wilfredo.poke.module.di.ViewModelFactory
import com.wilfredo.poke.module.domain.model.Pokemon
import com.wilfredo.poke.module.ui.pokemondetail.PokemonDetailFragment
import kotlinx.coroutines.launch

class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PokemonListViewModel> {
        ViewModelFactory(binding.root.context, ModuleViewModel.PokemonList)
    }

    private lateinit var adapter: PokemonAdapter

    private var isLoading = false
    private var currentPage = 0
    private val pageSize = 25

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
        initUiState()
        viewModel.getPokemon(pageSize, currentPage * pageSize)
    }

    private fun initElements() {

        binding.toolbar.title = getString(R.string.app_title)
        binding.toolbar.setNavigationOnClickListener {
            activity?.finish()
        }

        adapter =
            PokemonAdapter(this::openPokemonDetail, this::toggleFavorite, this::showImageExpanded)
        binding.rvPokemon.adapter = adapter

        binding.rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && lastVisibleItem >= totalItemCount - 1) {
                    loadNextPage()
                }
            }
        })
    }

    private fun loadNextPage() {
        isLoading = true
        lifecycleScope.launch {
            viewModel.getPokemon(pageSize, currentPage * pageSize)
            currentPage++
            isLoading = false
        }
    }

    private fun initUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { state ->
                binding.pbLoading.isVisible = state == PokemonListUiState.Loading
                when (state) {
                    is PokemonListUiState.Success -> {
                        adapter.submitList(state.pokemon)
                    }

                    is PokemonListUiState.Error -> Toast.makeText(
                        binding.root.context,
                        getString(R.string.error_service),
                        Toast.LENGTH_SHORT
                    ).show()

                    else -> Unit
                }
            }
        }
    }

    private fun openPokemonDetail(pokemon: Pokemon) {
        findNavController().navigate(
            R.id.action_poke_list_to_pokemon_detail,
            PokemonDetailFragment.getBundle(pokemon.id)
        )
    }

    private fun toggleFavorite(pokemon: Pokemon) {
        viewModel.toggleFavorite(pokemon.id, pokemon.isFavorite)
    }

    private fun showImageExpanded(pokemon: Pokemon) {
        val dialog = Dialog(binding.root.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_expanded_image)

        val toolbar = dialog.findViewById<MaterialToolbar>(R.id.toolbar)
        val imageExpanded = dialog.findViewById<ImageView>(R.id.iv_expanded)

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
            .into(imageExpanded)

        toolbar.setNavigationOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        dialog.show()
    }
}