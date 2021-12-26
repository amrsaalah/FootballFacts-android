package com.football.facts.domain.usecase

import com.football.facts.domain.base.BaseUseCase
import com.football.facts.domain.entity.Team
import com.football.facts.domain.repository.FootballRepository
import com.football.facts.domain.valueObject.Resource
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class SearchTeamsUseCase @Inject constructor(
    private val footballRepository: FootballRepository
) : BaseUseCase() {

    operator fun invoke(querySearch: MutableStateFlow<String>): Flow<Resource<List<Team>>> {
        return querySearch.debounce(seconds(0.5)).flatMapLatest { query ->
            if (query.isEmpty() || query.length < 4) return@flatMapLatest flowOf(Resource.error())
            safeCall {
                footballRepository.searchTeams(query)
            }
        }
    }
}