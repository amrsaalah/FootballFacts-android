package com.football.facts.domain.entity

import com.football.facts.domain.valueObject.ELeagueType

class League(
    val id : String,
    val name : String,
    val type : ELeagueType,
    val logoIcon : String ,
    var isFavorite : Boolean
)