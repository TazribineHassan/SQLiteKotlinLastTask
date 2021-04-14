package com.example.sqlitewithrecyclerview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlitewithrecyclerview.R
import com.example.sqlitewithrecyclerview.database.DbManager
import com.example.sqlitewithrecyclerview.databinding.ActivityShowOneStudentBinding
import com.example.sqlitewithrecyclerview.fragments.SowOneStudentFragment

class showOneStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowOneStudentBinding
    private val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowOneStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val student_id = intent.extras?.getInt("student_id")

        val trans = manager.beginTransaction()
        val dbManager = DbManager(this)
        val student = student_id?.let { dbManager.getStudent(it) }
        val oneStudentFragment = student?.let { SowOneStudentFragment(it) }
        if(!oneStudentFragment?.isAdded!!){
            if (oneStudentFragment != null) {
                trans.add(R.id.oneStudentFrame, oneStudentFragment).commit()
            }
        }
    }
}