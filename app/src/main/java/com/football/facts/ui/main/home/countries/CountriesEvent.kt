package com.football.facts.ui.main.home.countries

sealed class CountriesScreenEvent {

}

class OnCountryClickedEvent(val countryDisplayItem: CountryDisplayItem) : CountriesScreenEvent(){

}