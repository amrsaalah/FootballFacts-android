package com.football.facts.ui.main.home.searchTeams

import androidx.lifecycle.viewModelScope
import com.football.facts.domain.usecase.SearchTeamsUseCase
import com.football.facts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchTeamsViewModel @Inject constructor(
    private val searchTeamsUseCase: SearchTeamsUseCase
) : BaseViewModel(), SearchTeamsScreenState {


    private val searchQuery = MutableStateFlow("")

    override val searchTeamsDisplay: StateFlow<SearchTeamsDisplay> = combine(
        searchTeamsUseCase(searchQuery),
        searchQuery
    ) { res, query ->
        val teams = res.data?.map { team ->
            TeamDisplayItem(
                id = team.id,
                icon = team.logoIcon,
                name = team.name,
                isFavorite = false
            )
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

    }
}