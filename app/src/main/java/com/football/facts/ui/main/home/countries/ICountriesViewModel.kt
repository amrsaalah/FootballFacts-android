package com.football.facts.ui.main.home.countries

import kotlinx.coroutines.flow.MutableStateFlow

interface ICountriesViewModel {

    fun onEvent(event: CountriesScreenEvent)
    val countriesState : MutableStateFlow<CountriesState>
}