package com.football.facts.ui.main.home.countries

import androidx.lifecycle.viewModelScope
import com.football.facts.domain.entity.Country
import com.football.facts.domain.usecase.GetCountriesUseCase
import com.football.facts.domain.valueObject.Resource
import com.football.facts.domain.valueObject.Status
import com.football.facts.ui.base.BaseViewModel
import com.football.facts.ui.utils.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val navigationManager: NavigationManager
) : BaseViewModel(), CountriesScreenState {

    companion object {
        private const val TAG = "CountriesViewModel"
    }

    private val countriesUseCase = MutableStateFlow<Flow<Resource<List<Country>>>>(emptyFlow())

    private var countriesDisplayList: List<CountryDisplayItem> = arrayListOf()

    private val countryHashMap = hashMapOf<CountryDisplayItem, Country>()

    override val countriesDisplay = countriesUseCase.flatMapLatest { useCase ->
        useCase.map { res ->
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

            countriesDisplayList = countries
            CountriesDisplay(
                countries = countries,
                isProgressVisible = res.status == Status.LOADING
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, CountriesDisplay.initial())

    override fun onCountryClicked(item: CountryDisplayItem) {
        val country = countryHashMap[item] ?: return
        navigationManager.navigate(CountriesFragmentDirections.actionToLeaguesFragment(country))
    }


    fun getCountries() {
        Timber.d("getCountries: ")
        countriesUseCase.value = getCountriesUseCase()
    }
}