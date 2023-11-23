package com.mypokedex.db.shared

import com.mypokedex.db.AppDatabase
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import commypokedexdb.MypokedexQueries
import commypokedexdb.Pokemon
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
    get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
    driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
    public override val mypokedexQueries: MypokedexQueriesImpl = MypokedexQueriesImpl(this, driver)

    public object Schema : SqlDriver.Schema {
        public override val version: Int
            get() = 1

        public override fun create(driver: SqlDriver): Unit {
            driver.execute(null, """
          |CREATE TABLE Pokemon(
          |    name TEXT NOT NULL,
          |    url TEXT NOT NULL
          |)
          """.trimMargin(), 0)
        }

        public override fun migrate(
            driver: SqlDriver,
            oldVersion: Int,
            newVersion: Int
        ): Unit {
        }
    }
}

private class MypokedexQueriesImpl(
    private val database: AppDatabaseImpl,
    private val driver: SqlDriver
) : TransacterImpl(driver), MypokedexQueries {
    internal val selectAllPokemon: MutableList<Query<*>> = copyOnWriteList()

    public override fun <T : Any> selectAllPokemon(mapper: (name: String, url: String) -> T): Query<T>
            = Query(524318403, selectAllPokemon, driver, "mypokedex.sq", "selectAllPokemon",
        "SELECT Pokemon.* FROM Pokemon") { cursor ->
        mapper(
            cursor.getString(0)!!,
            cursor.getString(1)!!
        )
    }

    public override fun selectAllPokemon(): Query<Pokemon> = selectAllPokemon { name, url ->
        Pokemon(
            name,
            url
        )
    }

    public override fun insertPokemon(name: String, url: String): Unit {
        driver.execute(1251116869, """INSERT INTO Pokemon(name,url) VALUES (?,?)""", 2) {
            bindString(1, name)
            bindString(2, url)
        }
        notifyQueries(1251116869, {database.mypokedexQueries.selectAllPokemon})
    }
}
