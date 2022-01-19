package com.football.facts.domain.usecase

import com.football.facts.domain.base.BaseUseCase
import com.football.facts.domain.entity.Favorite
import com.football.facts.domain.repository.FootballRepository
import com.football.facts.domain.valueObject.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val footballRepository: FootballRepository
) : BaseUseCase() {

    operator fun invoke(favorite: Favorite): Flow<Resource<Unit>> {
        return safeCall {
            footballRepository.updateFavorite(favorite, false)
        }
    }
}