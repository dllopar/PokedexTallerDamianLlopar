package com.mypokedex.db

import com.mypokedex.db.shared.newInstance
import com.mypokedex.db.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import commypokedexdb.MypokedexQueries

public interface AppDatabase : Transacter {
    public val mypokedexQueries: MypokedexQueries

    public companion object {
        public val Schema: SqlDriver.Schema
            get() = AppDatabase::class.schema

        public operator fun invoke(driver: SqlDriver): AppDatabase =
            AppDatabase::class.newInstance(driver)
    }
}