package com.example.pokedextallerdamianllopar.datos.Repository.RepositoryDB

import com.example.pokedextallerdamianllopar.DatabaseDriverFactory
import com.example.pokedextallerdamianllopar.datos.PokedexResults
import com.mypokedex.db.AppDatabase

class RepositoryPokedexBD (databaseDriverFactory : DatabaseDriverFactory) {

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.mypokedexQueries

    fun insert(name:String, url:String){
        dbQuery.transaction {
            dbQuery.insertPokemon(name = name, url = url)
        }
    }
    fun get(): List <PokedexResults> {
        val results: List <PokedexResults> = dbQuery.selectAllPokemon(){
                name, url -> PokedexResults(name,url)
        }.executeAsList()

        return results
    }
}