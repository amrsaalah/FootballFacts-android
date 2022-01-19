package com.football.facts.domain.entity

class Team(
    val id : String ,
    val name : String ,
    val foundedYear : String?,
    val logoIcon : String? ,
    var isFavorite : Boolean
)