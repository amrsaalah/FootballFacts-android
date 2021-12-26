package com.football.facts.domain.valueObject

enum class ELeagueType(private val type: String) {
    LEAGUE("League"), CUP("Cup");

    companion object {
        private val map = values().associateBy(ELeagueType::type)

        fun fromType(type: String): ELeagueType = map[type] ?: error("")
    }
}