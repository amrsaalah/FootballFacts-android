package com.football.facts.ui.main.home.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.football.facts.R
import com.football.facts.databinding.FragmentFavoritesBinding
import com.football.facts.ui.base.BaseFragment
import com.football.facts.ui.utils.extensions.collectWithLifecycle
import com.football.facts.ui.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {
    override val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val viewModel by viewModels<FavoritesViewModel>()

    private val leaguesAdapter by lazy {
        FavoritesAdapter(viewModel)
    }

    private val teamsAdapter by lazy {
        FavoritesAdapter(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewLeagues.adapter = leaguesAdapter
        binding.recyclerViewTeams.adapter = teamsAdapter

        collectWithLifecycle(viewModel.favoritesDisplay){
            leaguesAdapter.submitList(it.favoriteLeagues)
            teamsAdapter.submitList(it.favoriteTeams)
        }
    }


}