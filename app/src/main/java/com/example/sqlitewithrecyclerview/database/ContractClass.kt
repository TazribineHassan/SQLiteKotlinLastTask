package com.example.sqlitewithrecyclerview.database

import android.provider.BaseColumns

object ContractClass {
    object  Student : BaseColumns{

        // names of  columns and table
        const val TABLE_NAME = "etudiant"
        const val NOM_COLUMN = "nom"
        const val PRENOM_COLUMN = "prenom"
        const val AGE_COLUMN = "age"
        const val PATH_COLUMN = "path"

        // script
        const val SQL_CREATE_TABLE = "CREATE TABLE $TABLE_NAME(" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NOM_COLUMN TEXT," +
                "$PRENOM_COLUMN TEXT," +
                "$AGE_COLUMN TEXT," +
                "$PATH_COLUMN TEXT)"
        const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    }
}