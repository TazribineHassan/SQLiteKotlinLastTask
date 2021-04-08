package com.example.sqlitewithrecyclerview

import android.provider.BaseColumns

object ContractClass {
    object  Student : BaseColumns{

        // names of  columns and table
        const val TABLE_NAME = "etudiant"
        const val FIRST_COLUMN_NAME = "nom"
        const val SECOND_COLUMN_NAME = "prenom"
        const val THIRD_COLUMN_NAME = "age"
        const val FOURTH_COLUMN_NAME = "path"

        // script
        const val SQL_SCRIPT = ""
        const val DROP_TABLE = ""

    }
}