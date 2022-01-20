package com.football.facts.ui.main.home.leagues

import androidx.lifecycle.viewModelScope
import com.football.facts.domain.entity.Country
import com.football.facts.domain.entity.League
import com.football.facts.domain.usecase.GetLeaguesByCountryCodeUseCase
import com.football.facts.domain.usecase.UpdateLeagueFavoriteUseCase
import com.football.facts.domain.valueObject.Resource
import com.football.facts.domain.valueObject.Status
import com.football.facts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel @Inject constructor(
    private val getLeaguesByCountryCodeUseCase: GetLeaguesByCountryCodeUseCase,
    private val updateLeagueFavoriteUseCase: UpdateLeagueFavoriteUseCase
) : BaseViewModel(), LeaguesScreenState {


    private lateinit var country: Country
    private val leaguesUseCase = MutableStateFlow<Flow<Resource<List<League>>>>(emptyFlow())
    private val currentFavoriteItem = MutableStateFlow<League?>(null)
    private val leaguesHashMap : LinkedHashMap<LeagueDisplayItem, League> = linkedMapOf()

    fun init(country: Country) {
        this.country = country
        refresh()
    }

    override fun onLeagueClicked(item: LeagueDisplayItem) {

    }

    override fun onFavoriteClicked(item: LeagueDisplayItem) {
        val league = leaguesHashMap[item] ?: return
        viewModelScope.launch {
            updateLeagueFavoriteUseCase(league).collect { res ->
                if (res.isSuccess()) {
                    currentFavoriteItem.value = res.data
                }
            }
        }
    }

    override val leaguesDisplay = leaguesUseCase.flatMapLatest { useCase ->
        combine(useCase, currentFavoriteItem) { res, currentFavLeague ->
            if (res.isSuccess()) {
                leaguesHashMap.clear()
            }

            val leagues = res.data?.map { league ->
                val newIsFavorite = if (league.id == currentFavLeague?.id) {
                    currentFavLeague.isFavorite
                } else null

                val item = LeagueDisplayItem(
                    id = league.id,
                    icon = league.logoIcon,
                    name = league.name,
                    isFavorite = newIsFavorite ?: league.isFavorite
                )
                leaguesHashMap[item] = league
                item
            }


            LeaguesDisplay(
                leagues = leagues ?: ArrayList(leaguesHashMap.keys),
                isProgressVisible = res.status == Status.LOADING
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, LeaguesDisplay.initial())


    fun refresh() {
        leaguesUseCase.value = getLeaguesByCountryCodeUseCase(country.code)
    }
}