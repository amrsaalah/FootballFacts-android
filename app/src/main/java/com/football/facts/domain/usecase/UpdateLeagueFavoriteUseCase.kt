package com.football.facts.domain.usecase

import com.football.facts.domain.base.BaseUseCase
import com.football.facts.domain.entity.League
import com.football.facts.domain.repository.FootballRepository
import com.football.facts.domain.valueObject.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateLeagueFavoriteUseCase @Inject constructor(
    private val footballRepository: FootballRepository
) : BaseUseCase() {

    operator fun invoke(league : League) : Flow<Resource<Unit>>{
        return safeCall {
            footballRepository.updateLeagueFavorite(league , !league.isFavorite)
            league.isFavorite = !league.isFavorite
        }
    }
}