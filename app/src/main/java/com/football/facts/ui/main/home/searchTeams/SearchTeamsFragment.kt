package com.football.facts.ui.main.home.searchTeams

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.football.facts.ui.base.BaseComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchTeamsFragment : BaseComposeFragment() {
    override val viewModel by viewModels<SearchTeamsViewModel>()


    @Composable
    override fun SetComposeContent() {
        SearchTeamsScreen(viewModel)
    }
}