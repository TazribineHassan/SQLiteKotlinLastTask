package com.example.sqlitewithrecyclerview.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.widget.Toast
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.NOM_COLUMN
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.PRENOM_COLUMN
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.AGE_COLUMN
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.PATH_COLUMN
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.TABLE_NAME
import com.example.sqlitewithrecyclerview.classes.Student

class DbManager (val context: Context) {

    val dbHelper : MyHelper = MyHelper(context)

    fun getStudent(student_id: Int) : Student{

        val db = dbHelper.readableDatabase
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$student_id")
        val cursor = db.query(
            TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null)

        val student : Student
        with(cursor){
           moveToFirst()
           student = Student(
               getInt(getColumnIndexOrThrow(BaseColumns._ID)), //id
               getString(getColumnIndexOrThrow(NOM_COLUMN)),  //nom
               getString(getColumnIndexOrThrow(PRENOM_COLUMN)),  //prenom
               getInt(getColumnIndexOrThrow(AGE_COLUMN)),  //age
               getString(getColumnIndexOrThrow(PATH_COLUMN))  //path to image
           )
        }
        return student
    }

    fun add(student: Student){
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        with(values){
            put(NOM_COLUMN, student.nom)
            put(PRENOM_COLUMN, student.prenom)
            put(AGE_COLUMN, student.age)
            put(PATH_COLUMN, student.path)
        }

        val rowAffected = db?.insert(TABLE_NAME, null, values)
        if (rowAffected != null) {
            if (rowAffected >= 1){
                Toast.makeText(context, "${student.nom} ${student.prenom} a ete ajouter", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun delete(id : Int){
        val db = dbHelper.writableDatabase
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$id")
        val deletedRows = db.delete(TABLE_NAME, selection, selectionArgs)
        if (deletedRows >= 1) {
            if (deletedRows >= 1){
                Toast.makeText(context, "L'etudiant a ete supprimer", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getAllItemsCursor(): Cursor? {

        val db = dbHelper.readableDatabase

        val cursor = db?.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)

        return cursor

    }

    fun update(student: Student){

        val db = dbHelper.writableDatabase

        val values = ContentValues()
        with(values){
            put(NOM_COLUMN, student.nom)
            put(PRENOM_COLUMN, student.prenom)
            put(NOM_COLUMN, student.age)
            put(PRENOM_COLUMN, student.path)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("${student.id}")
        val updatedRows = db.update(TABLE_NAME,values, selection, selectionArgs)
        if (updatedRows >= 1) {
            if (updatedRows >= 1){
                Toast.makeText(context, "L'etudiant $student.nom ${student.prenom} a ete modifier", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun close(){
        dbHelper.close()
    }
}