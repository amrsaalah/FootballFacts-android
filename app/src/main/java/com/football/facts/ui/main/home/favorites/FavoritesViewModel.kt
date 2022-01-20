package com.football.facts.ui.main.home.favorites

import androidx.lifecycle.viewModelScope
import com.football.facts.domain.entity.Favorite
import com.football.facts.domain.usecase.GetFavoritesUseCase
import com.football.facts.domain.usecase.RemoveFavoriteUseCase
import com.football.facts.domain.valueObject.EFavoriteType.LEAGUE
import com.football.facts.domain.valueObject.EFavoriteType.TEAM
import com.football.facts.domain.valueObject.Resource
import com.football.facts.ui.base.BaseViewModel
import com.football.facts.ui.utils.broadcast.BroadcastManager
import com.football.facts.ui.utils.broadcast.Broadcasts
import com.football.facts.ui.utils.eventBus.AppEvent
import com.football.facts.ui.utils.eventBus.AppEvent.REFRESH_SEARCH_TEAMS
import com.football.facts.ui.utils.eventBus.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val eventBus: EventBus
) : BaseViewModel(), FavoritesScreenState {


    private var leagues: ArrayList<Favorite>? = null
    private var teams: ArrayList<Favorite>? = null

    private val getTeamsAndLeaguesFavorites =
        MutableStateFlow<Flow<Pair<Resource<List<Favorite>>, Resource<List<Favorite>>>>>(emptyFlow())

    private val favoritesHashMap: LinkedHashMap<FavoriteDisplayItem, Favorite> = linkedMapOf()
    private val removeFavoriteItem = MutableStateFlow<Favorite?>(null)

    override val favoritesDisplay: StateFlow<FavoritesDisplay> =
        getTeamsAndLeaguesFavorites.flatMapLatest { leaguesAndTeamsFLow ->
            combine(
                leaguesAndTeamsFLow,
                removeFavoriteItem
            ) { leaguesAndTeams, removeItem ->

                val leaguesData = leaguesAndTeams.first.data
                val teamsData = leaguesAndTeams.second.data

                if (leaguesData != null && this.leagues == null) {
                    this.leagues = ArrayList(leaguesData)
                }

                if (teamsData != null && this.teams == null) {
                    this.teams = ArrayList(teamsData)
                }


                if (removeItem != null) {
                    when (removeItem.type) {
                        LEAGUE -> {
                            this.leagues?.removeAll {
                                it.id == removeItem.id
                            }
                        }
                        TEAM -> {
                            this.teams?.removeAll {
                                it.id == removeItem.id
                            }
                        }
                    }
                }

                val favoriteTeams = this.teams?.map { team ->
                    val item = FavoriteDisplayItem(
                        id = team.id,
                        name = team.name,
                        icon = team.icon
                    )
                    favoritesHashMap[item] = team
                    item
                } ?: listOf()

                val favoriteLeagues = this.leagues?.map { league ->
                    val item = FavoriteDisplayItem(
                        id = league.id,
                        name = league.name,
                        icon = league.icon
                    )
                    favoritesHashMap[item] = league
                    item
                } ?: listOf()

                FavoritesDisplay(
                    favoriteLeagues = favoriteLeagues,
                    favoriteTeams = favoriteTeams
                )
            }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, FavoritesDisplay.initial())


    override fun onFavoriteClicked(item: FavoriteDisplayItem) {
        val favorite = favoritesHashMap[item] ?: return
        viewModelScope.launch {
            removeFavoriteUseCase(favorite).collect { res ->
                if (res.isSuccess()) {
                    removeFavoriteItem.value = favorite
                    eventBus.invokeEvent(REFRESH_SEARCH_TEAMS)
                }
            }
        }
    }


    fun refresh() {
        removeFavoriteItem.value = null
        teams = null
        leagues = null
        getTeamsAndLeaguesFavorites.value =
            combine(getFavoritesUseCase(LEAGUE), getFavoritesUseCase(TEAM)) { leagues, teams ->
                Pair(leagues, teams)
            }
    }
}