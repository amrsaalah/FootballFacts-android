package com.football.facts.ui.main.home.leagues

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.football.facts.domain.entity.Country
import com.football.facts.ui.base.BaseComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaguesFragment : BaseComposeFragment() {
    override val viewModel by viewModels<LeaguesViewModel>()

    private val country: Country by lazy {
        LeaguesFragmentArgs.fromBundle(requireArguments()).country
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(country)
    }


    @Composable
    override fun SetComposeContent() {
        LeaguesScreen(viewModel)
    }
}