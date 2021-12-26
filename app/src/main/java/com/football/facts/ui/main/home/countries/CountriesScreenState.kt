package com.football.facts.ui.main.home.countries

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface CountriesScreenState {
    val countriesDisplay: StateFlow<CountriesDisplay>
    fun onCountryClicked(item: CountryDisplayItem)

    companion object {
        fun preview(): CountriesScreenState {
            return object : CountriesScreenState {

                override val countriesDisplay: MutableStateFlow<CountriesDisplay> = MutableStateFlow(
                    CountriesDisplay.preview()
                )

                override fun onCountryClicked(item: CountryDisplayItem) {

                }
            }
        }
    }
}

data class CountriesDisplay(
    val isProgressVisible: Boolean,
    val countries: List<CountryDisplayItem>
) {
    companion object {
        fun initial(): CountriesDisplay {
            return CountriesDisplay(
                isProgressVisible = false,
                countries = listOf()
            )
        }

        fun preview(): CountriesDisplay {
            return CountriesDisplay(
                isProgressVisible = true,
                countries = listOf(
                    CountryDisplayItem(
                        name = "Egypt",
                        icon = null,
                        isIconSvg = false
                    ),

                    CountryDisplayItem(
                        name = "England",
                        icon = null,
                        isIconSvg = false
                    ),

                    CountryDisplayItem(
                        name = "Spain",
                        icon = null,
                        isIconSvg = false
                    )
                )
            )
        }
    }
}

data class CountryDisplayItem(
    val icon: String?,
    val name: String,
    val isIconSvg : Boolean
)