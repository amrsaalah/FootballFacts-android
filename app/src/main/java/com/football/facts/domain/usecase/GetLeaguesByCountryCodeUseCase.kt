package com.football.facts.domain.usecase

import com.football.facts.domain.base.BaseUseCase
import com.football.facts.domain.entity.League
import com.football.facts.domain.repository.FootballRepository
import com.football.facts.domain.valueObject.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLeaguesByCountryCodeUseCase @Inject constructor(
    private val footballRepository: FootballRepository
) : BaseUseCase() {

    operator fun invoke(countryCode : String?) : Flow<Resource<List<League>>> {
        return safeCall {
            footballRepository.getLeaguesByCountryCode(countryCode)
        }
    }
}