package com.football.facts.domain.valueObject

class FootballStatus(
    val currentRequestsNumber: Int,
    val limitRequestsNumberPerDay: Int
) {

    fun didPassMaxNumberOfRequestsPerDay() : Boolean{
        return currentRequestsNumber >= limitRequestsNumberPerDay
    }
}