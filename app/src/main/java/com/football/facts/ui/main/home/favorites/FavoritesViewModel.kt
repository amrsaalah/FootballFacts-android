package com.football.facts.ui.main.home.favorites

import androidx.lifecycle.viewModelScope
import com.football.facts.domain.usecase.GetCountriesUseCase
import com.football.facts.domain.valueObject.Status
import com.football.facts.ui.base.BaseViewModel
import com.football.facts.ui.main.home.countries.CountriesDisplay
import com.football.facts.ui.main.home.countries.CountryDisplayItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : BaseViewModel() {


    private val updateEventChannel = MutableSharedFlow<CountryDisplayItem>()
    val events = updateEventChannel.asSharedFlow()

//    val countriesDisplay = getCountriesUseCase().map { res ->
//        val countries = res.data?.map { country ->
//            CountryDisplayItem(
//                icon = country.flagIcon,
//                name = country.name,
//                isIconSvg = country.isFlagIconSvg()
//            )
//        } ?: listOf()
//
//        CountriesDisplay(
//            countries = countries,
//            isProgressVisible = res.status == Status.LOADING
//        )
//    }.stateIn(viewModelScope, SharingStarted.Lazily, CountriesDisplay.initial())



    fun onItemClicked(item: CountryDisplayItem){
        viewModelScope.launch {
            updateEventChannel.emit(item.copy(name = "Test update"))
        }
    }

}