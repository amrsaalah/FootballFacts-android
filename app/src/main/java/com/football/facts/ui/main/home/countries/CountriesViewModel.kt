package com.football.facts.ui.main.home.countries

import androidx.lifecycle.viewModelScope
import com.football.facts.domain.entity.Country
import com.football.facts.domain.usecase.GetCountriesUseCase
import com.football.facts.ui.base.BaseViewModel
import com.football.facts.ui.utils.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val navigationManager: NavigationManager
) : BaseViewModel() , ICountriesViewModel {

    companion object {
        private const val TAG = "CountriesViewModel"
    }


    private var countriesDisplayList: List<CountryDisplayItem> = arrayListOf()

    private val countryHashMap = hashMapOf<CountryDisplayItem, Country>()

    override val countriesState = MutableStateFlow(CountriesState.initial())

    override fun onEvent(event: CountriesScreenEvent) {
        when (event) {
            is OnCountryClickedEvent -> {
                onCountryClicked(event.countryDisplayItem)
            }
        }
    }

    private fun onCountryClicked(item: CountryDisplayItem) {
        val country = countryHashMap[item] ?: return
        navigationManager.navigate(CountriesFragmentDirections.actionToLeaguesFragment(country))
    }


    fun getCountries() {
        viewModelScope.launch {
            getCountriesUseCase().collect { res ->
                countryHashMap.clear()
                val countries = res.data?.map { country ->
                    val item = CountryDisplayItem(
                        icon = country.flagIcon,
                        name = country.name,
                        isIconSvg = country.isFlagIconSvg()
                    )
                    countryHashMap[item] = country
                    item
                } ?: countriesDisplayList

                countriesState.value = countriesState.value.copy(
                    isProgressVisible = res.isLoading(),
                    countries = countries
                )
            }
        }
    }
}