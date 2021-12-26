package com.football.facts.domain.base

import com.football.facts.domain.valueObject.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase {

    fun <T> safeCall(
        apiCall: suspend () -> T
    ): Flow<Resource<T>> {
        return flow {
            emit(Resource.loading())
            try {
                emit(Resource.success(apiCall.invoke()))
            } catch (exception: Exception) {
                val error: Resource<T> = Resource.error(
                    errorMessage = exception.message
                )
                emit(error)
            }
        }
    }
}