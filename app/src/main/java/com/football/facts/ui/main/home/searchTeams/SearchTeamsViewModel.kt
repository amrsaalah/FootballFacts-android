package com.football.facts.ui.main.home.searchTeams

import androidx.lifecycle.viewModelScope
import com.football.facts.domain.entity.Team
import com.football.facts.domain.usecase.SearchTeamsUseCase
import com.football.facts.domain.usecase.UpdateTeamFavoriteUseCase
import com.football.facts.ui.base.BaseViewModel
import com.football.facts.ui.main.home.leagues.LeagueDisplayItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchTeamsViewModel @Inject constructor(
    private val searchTeamsUseCase: SearchTeamsUseCase ,
    private val updateTeamFavoriteUseCase: UpdateTeamFavoriteUseCase
) : BaseViewModel(), SearchTeamsScreenState {


    private val searchQuery = MutableStateFlow("")
    private val teamsHashMap : LinkedHashMap<TeamDisplayItem , Team> = linkedMapOf()
    private val currentFavoriteItem = MutableStateFlow<Team?>(null)

    override val searchTeamsDisplay: StateFlow<SearchTeamsDisplay> = combine(
        searchTeamsUseCase(searchQuery),
        searchQuery ,
        currentFavoriteItem
    ) { res, query , currentFavTeam ->
        val teams = res.data?.map { team ->

            val newIsFavorite = if (team.id == currentFavTeam?.id) {
                currentFavTeam.isFavorite
            } else null

            val item = TeamDisplayItem(
                id = team.id,
                icon = team.logoIcon,
                name = team.name,
                isFavorite = newIsFavorite ?: team.isFavorite
            )
            teamsHashMap[item] = team
            item
        } ?: listOf()

        SearchTeamsDisplay(
            isProgressVisible = res.isLoading(),
            teams = teams,
            querySearch = query
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, SearchTeamsDisplay.initial())


    override fun onSearchQueryChanged(querySearch: String) {
        searchQuery.value = querySearch
    }

    override fun onTeamClicked(item: TeamDisplayItem) {

    }

    override fun onFavoriteClicked(item: TeamDisplayItem) {
        val team = teamsHashMap[item] ?: return
        viewModelScope.launch {
            updateTeamFavoriteUseCase(team).collect { res ->
                if (res.isSuccess()) {
                    currentFavoriteItem.value = res.data
                }
            }
        }
    }
}