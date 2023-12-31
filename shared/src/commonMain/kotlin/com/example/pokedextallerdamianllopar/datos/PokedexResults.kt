package com.example.pokedextallerdamianllopar.datos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexResults(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String
)