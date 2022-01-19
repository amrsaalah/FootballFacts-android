package com.football.facts.ui.main.home.leagues

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface LeaguesScreenState {
    val leaguesDisplay: StateFlow<LeaguesDisplay>
    fun onLeagueClicked(item: LeagueDisplayItem)
    fun onFavoriteClicked(item: LeagueDisplayItem)

    companion object {
        fun preview(): LeaguesScreenState {
            return object : LeaguesScreenState {

                override val leaguesDisplay: MutableStateFlow<LeaguesDisplay> = MutableStateFlow(
                    LeaguesDisplay.preview()
                )

                override fun onLeagueClicked(item: LeagueDisplayItem) {

                }

                override fun onFavoriteClicked(item: LeagueDisplayItem) {

                }
            }
        }
    }
}

data class LeaguesDisplay(
    val isProgressVisible: Boolean,
    val leagues: List<LeagueDisplayItem>
) {
    companion object {
        fun initial(): LeaguesDisplay {
            return LeaguesDisplay(
                isProgressVisible = false,
                leagues= listOf()
            )
        }

        fun preview(): LeaguesDisplay {
            return LeaguesDisplay(
                isProgressVisible = true,
                leagues = listOf(
                    LeagueDisplayItem(
                        id = "1",
                        name = "Premier league",
                        icon = null,
                        isFavorite = false
                    ),

                    LeagueDisplayItem(
                        id = "2",
                        name = "La liga",
                        icon = null,
                        isFavorite = true
                    )
                )
            )
        }
    }
}

data class LeagueDisplayItem(
    val id : String,
    val icon: String?,
    val name: String ,
    val isFavorite : Boolean
)