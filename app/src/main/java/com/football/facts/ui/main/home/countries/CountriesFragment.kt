package com.football.facts.ui.main.home.countries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.football.facts.R
import com.football.facts.databinding.FragmentCountriesBinding
import com.football.facts.ui.base.BaseFragment
import com.football.facts.ui.theme.FootballFactsTheme
import com.football.facts.ui.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : BaseFragment(R.layout.fragment_countries) {
    override val binding by viewBinding(FragmentCountriesBinding::bind)
    override val viewModel by viewModels<CountriesViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCountries()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.apply {
            setContent {
                FootballFactsTheme {
                    CountriesScreen(viewModel)
                }
            }
        }
    }
}