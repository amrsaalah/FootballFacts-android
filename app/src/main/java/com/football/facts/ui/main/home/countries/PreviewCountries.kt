package com.football.facts.ui.main.home.countries

import kotlinx.coroutines.flow.MutableStateFlow

class PreviewCountries : ICountriesViewModel {
    override fun onEvent(event: CountriesScreenEvent) {

    }

    override val countriesState: MutableStateFlow<CountriesState> = MutableStateFlow(CountriesState.preview())

}