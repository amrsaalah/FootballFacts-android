package com.football.facts.ui.main.home.searchTeams

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface SearchTeamsScreenState {
    val searchTeamsDisplay: StateFlow<SearchTeamsDisplay>
    fun onSearchQueryChanged(querySearch : String)
    fun onTeamClicked(item: TeamDisplayItem)
    fun onFavoriteClicked(item: TeamDisplayItem)

    companion object {
        fun preview(): SearchTeamsScreenState {
            return object : SearchTeamsScreenState {

                override val searchTeamsDisplay: MutableStateFlow<SearchTeamsDisplay> =
                    MutableStateFlow(
                        SearchTeamsDisplay.preview()
                    )

                override fun onSearchQueryChanged(querySearch: String) {

                }

                override fun onTeamClicked(item: TeamDisplayItem) {

                }

                override fun onFavoriteClicked(item: TeamDisplayItem) {

                }
            }
        }
    }
}

data class SearchTeamsDisplay(
    val isProgressVisible: Boolean,
    val teams: List<TeamDisplayItem> ,
    val querySearch : String
) {
    companion object {
        fun initial(): SearchTeamsDisplay {
            return SearchTeamsDisplay(
                isProgressVisible = false,
                teams = listOf() ,
                querySearch = ""
            )
        }

        fun preview(): SearchTeamsDisplay {
            return SearchTeamsDisplay(
                isProgressVisible = true,
                querySearch = "Real",
                teams = listOf(
                    TeamDisplayItem(
                        id = "1",
                        name = "Real Madrid",
                        icon = null,
                        isFavorite = false,
                    ),

                    TeamDisplayItem(
                        id = "2",
                        name = "Liverpool",
                        icon = null,
                        isFavorite = true
                    )
                )
            )
        }
    }
}

data class TeamDisplayItem(
    val id: String,
    val icon: String?,
    val name: String,
    val isFavorite: Boolean
)