package com.example.sqlitewithrecyclerview.classes

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitewithrecyclerview.R
import com.example.sqlitewithrecyclerview.activities.showOneStudentActivity
import com.example.sqlitewithrecyclerview.activities.showStudentsActivity
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.AGE_COLUMN
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.NOM_COLUMN
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.PATH_COLUMN
import com.example.sqlitewithrecyclerview.database.ContractClass.Student.PRENOM_COLUMN

class RecyclerCursorAdapter(val cursor:Cursor) :  RecyclerView.Adapter<RecyclerCursorAdapter.Holder>() {

    private lateinit var context : Context
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nom_prenom_view : TextView = itemView.findViewById(R.id.nom_prenom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.content_layout, parent ,false)
        context = itemView.context
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
            return cursor.count
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        cursor.moveToPosition(position)
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
        val nom = cursor.getString(cursor.getColumnIndexOrThrow(NOM_COLUMN))
        val prenom = cursor.getString(cursor.getColumnIndexOrThrow(PRENOM_COLUMN))
        holder.nom_prenom_view.text = "$nom $prenom"
        holder.itemView.setOnClickListener(View.OnClickListener {
            val my_intent = Intent(context, showOneStudentActivity::class.java)
            my_intent.putExtra("student_id", id)
            context.startActivity(my_intent)
        })
    }
}