package com.football.facts.ui.main.home.searchTeams

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.football.facts.R
import com.football.facts.databinding.FragmentTeamsSearchBinding
import com.football.facts.ui.base.BaseFragment
import com.football.facts.ui.theme.FootballFactsTheme
import com.football.facts.ui.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchTeamsFragment : BaseFragment(R.layout.fragment_teams_search) {
    override val binding by viewBinding(FragmentTeamsSearchBinding::bind)
    override val viewModel by viewModels<SearchTeamsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.apply {
            setContent {
                FootballFactsTheme {
                    SearchTeamsScreen(viewModel)
                }
            }
        }
    }
}