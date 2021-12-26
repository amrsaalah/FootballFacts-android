package com.football.facts.domain.valueObject

data class Resource<out T>(
    val status: Status, val data: T?, val message: String?
) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(
            errorMessage: String? = null
        ): Resource<T> {
            return Resource(Status.ERROR, null, errorMessage)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

    }

    fun isSuccess() : Boolean{
        return status == Status.SUCCESS
    }

    fun isError() : Boolean{
        return status == Status.ERROR
    }

    fun isLoading() : Boolean{
        return status == Status.LOADING
    }
}