package com.example.pokedextallerdamianllopar.android.presentation

import com.example.pokedextallerdamianllopar.datos.Pokedex

sealed class PokedexScreenState {
    object Loading : PokedexScreenState()

    object Error : PokedexScreenState()

    class ShowPokedex(val pokedex : Pokedex) : PokedexScreenState()
}