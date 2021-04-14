package com.example.sqlitewithrecyclerview.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlitewithrecyclerview.R
import com.example.sqlitewithrecyclerview.databinding.ActivityMainBinding
import com.example.sqlitewithrecyclerview.databinding.ActivityShowOneStudentBinding
import com.example.sqlitewithrecyclerview.databinding.ActivityShowStudentsBinding
import com.example.sqlitewithrecyclerview.fragments.showStudentsFragment
import com.example.sqlitewithrecyclerview.interfaces.showActivityListener

class showStudentsActivity : AppCompatActivity(), showActivityListener {
    private  lateinit var  binding: ActivityShowStudentsBinding
    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val trans = manager.beginTransaction()
        val listFragment = showStudentsFragment()
        if(!listFragment.isAdded){
            trans.add(R.id.recyclerFrame, listFragment).commit()
        }
    }

    override fun showActivity(activityType: String) {

    }
}