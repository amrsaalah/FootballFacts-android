package com.football.facts.ui.main.home.countries


data class CountriesState(
    val isProgressVisible: Boolean,
    val countries: List<CountryDisplayItem>
) {
    companion object {
        fun initial(): CountriesState {
            return CountriesState(
                isProgressVisible = false,
                countries = listOf()
            )
        }

        fun preview(): CountriesState {
            return CountriesState(
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