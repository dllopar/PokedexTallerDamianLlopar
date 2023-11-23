package commypokedexdb

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter

public interface MypokedexQueries : Transacter {
    public fun <T : Any> selectAllPokemon(mapper: (name: String, url: String) -> T): Query<T>

    public fun selectAllPokemon(): Query<Pokemon>

    public fun insertPokemon(name: String, url: String): Unit
}