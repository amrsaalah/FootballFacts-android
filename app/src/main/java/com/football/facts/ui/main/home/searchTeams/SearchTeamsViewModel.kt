package com.football.facts.ui.main.home.searchTeams

import androidx.lifecycle.viewModelScope
import com.football.facts.domain.entity.Team
import com.football.facts.domain.usecase.SearchTeamsUseCase
import com.football.facts.domain.usecase.UpdateTeamFavoriteUseCase
import com.football.facts.ui.base.BaseViewModel
import com.football.facts.ui.utils.eventBus.AppEvent
import com.football.facts.ui.utils.eventBus.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchTeamsViewModel @Inject constructor(
    private val searchTeamsUseCase: SearchTeamsUseCase,
    private val updateTeamFavoriteUseCase: UpdateTeamFavoriteUseCase,
    private val eventBus: EventBus
) : BaseViewModel(), SearchTeamsScreenState {


    init {
        viewModelScope.launch {
            eventBus.events.collectLatest { event ->
                if (event == AppEvent.REFRESH_SEARCH_TEAMS) {
                    refresh()
                }
            }
        }
    }

    private val searchQuery = MutableStateFlow(SearchTeamsUseCase.QuerySearchHolder(""))
    private val teamsHashMap: LinkedHashMap<TeamDisplayItem, Team> = linkedMapOf()
    private val currentFavoriteItem = MutableStateFlow<Team?>(null)

    override val searchTeamsDisplay: StateFlow<SearchTeamsDisplay> = combine(
        searchTeamsUseCase(searchQuery),
        searchQuery,
        currentFavoriteItem
    ) { res, query, currentFavTeam ->
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
            querySearch = query.query
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, SearchTeamsDisplay.initial())


    override fun onSearchQueryChanged(querySearch: String) {
        searchQuery.value = SearchTeamsUseCase.QuerySearchHolder(querySearch)
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


    private fun refresh() {
        currentFavoriteItem.value = null
        teamsHashMap.clear()
        searchQuery.value = searchQuery.value.copy(isRefresh = true)
    }
}