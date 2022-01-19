package com.football.facts.domain.usecase

import com.football.facts.domain.base.BaseUseCase
import com.football.facts.domain.entity.Favorite
import com.football.facts.domain.entity.League
import com.football.facts.domain.entity.Team
import com.football.facts.domain.repository.FootballRepository
import com.football.facts.domain.valueObject.EFavoriteType
import com.football.facts.domain.valueObject.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTeamFavoriteUseCase @Inject constructor(
    private val footballRepository: FootballRepository
) : BaseUseCase() {

    operator fun invoke(team: Team): Flow<Resource<Team>> {
        return safeCall {
            footballRepository.updateFavorite(
                Favorite(
                    id = team.id,
                    name = team.name,
                    icon = team.logoIcon,
                    type = EFavoriteType.TEAM
                ), !team.isFavorite
            )
            team.isFavorite = !team.isFavorite
            team
        }
    }
}