package com.football.facts.domain.entity

import java.io.Serializable
import java.util.*

class Country(
    val name : String ,
    val code : String? ,
    val flagIcon : String?
) : Serializable {

    companion object{
        private const val SVG_TYPE = "svg"
        private const val ENGLISH_NAME = "England"
        private const val SPAIN_NAME = "Spain"
        private const val ITALY_NAME = "Italy"
        private const val GERMANY_NAME = "Germany"
    }

    fun isFlagIconSvg() : Boolean{
        return flagIcon?.lowercase(Locale.ENGLISH)?.split(".")?.lastOrNull() == SVG_TYPE
    }

    fun isTop4CountryInEurope() : Boolean{
        val top4Countries =  listOf(SPAIN_NAME , ENGLISH_NAME , ITALY_NAME , GERMANY_NAME)
        return top4Countries.contains(name)
    }
}