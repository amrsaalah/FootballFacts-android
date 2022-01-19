package com.football.facts.domain.entity

import com.football.facts.domain.valueObject.EFavoriteType

class Favorite(
    val id : String,
    val name : String?,
    val icon : String?,
    val type : EFavoriteType
)