package com.football.facts.domain.usecase

import com.football.facts.domain.base.BaseUseCase
import com.football.facts.domain.entity.Favorite
import com.football.facts.domain.repository.FootballRepository
import com.football.facts.domain.valueObject.EFavoriteType
import com.football.facts.domain.valueObject.EFavoriteType.*
import com.football.facts.domain.valueObject.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val footballRepository: FootballRepository
) : BaseUseCase() {

    operator fun invoke(type: EFavoriteType): Flow<Resource<List<Favorite>>> {
        return when (type) {
            LEAGUE -> {
                safeCall {
                    footballRepository.getFavoriteLeagues()
                }
            }
            TEAM -> {
                safeCall {
                    footballRepository.getFavoriteTeams()
                }
            }
        }
    }

}