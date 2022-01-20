package com.football.facts.ui.main.home.countries

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.football.facts.ui.base.BaseComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : BaseComposeFragment() {

    override val viewModel by viewModels<CountriesViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCountries()
    }


    @Composable
    override fun SetComposeContent() {
        CountriesScreen(viewModel)
    }

}