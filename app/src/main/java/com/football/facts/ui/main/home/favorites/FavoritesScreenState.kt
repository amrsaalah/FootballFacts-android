package com.football.facts.ui.main.home.favorites

import kotlinx.coroutines.flow.StateFlow

interface FavoritesScreenState {
    val favoritesDisplay: StateFlow<FavoritesDisplay>
    fun onFavoriteClicked(item: FavoriteDisplayItem)
}


data class FavoritesDisplay(
    val favoriteTeams: List<FavoriteDisplayItem>,
    val favoriteLeagues: List<FavoriteDisplayItem>
) {
    companion object {
        fun initial(): FavoritesDisplay {
            return FavoritesDisplay(
                favoriteTeams = listOf(),
                favoriteLeagues = listOf()
            )
        }
    }
}


data class FavoriteDisplayItem(
    val id: String,
    val name: String?,
    val icon: String?
)