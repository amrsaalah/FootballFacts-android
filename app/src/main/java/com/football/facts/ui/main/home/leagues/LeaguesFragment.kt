package com.football.facts.ui.main.home.leagues

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.football.facts.R
import com.football.facts.databinding.FragmentLeaguesBinding
import com.football.facts.domain.entity.Country
import com.football.facts.ui.base.BaseFragment
import com.football.facts.ui.theme.FootballFactsTheme
import com.football.facts.ui.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaguesFragment : BaseFragment(R.layout.fragment_leagues) {
    override val viewModel by viewModels<LeaguesViewModel>()
    override val binding by viewBinding(FragmentLeaguesBinding::bind)

    private val country: Country by lazy {
        LeaguesFragmentArgs.fromBundle(requireArguments()).country
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(country)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.apply {
            setContent {
                FootballFactsTheme {
                    LeaguesScreen(viewModel)
                }
            }
        }
    }
}