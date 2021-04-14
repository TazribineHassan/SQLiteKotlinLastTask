package com.example.sqlitewithrecyclerview.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlitewithrecyclerview.fragments.MainFragment
import com.example.sqlitewithrecyclerview.R
import com.example.sqlitewithrecyclerview.databinding.ActivityMainBinding
import com.example.sqlitewithrecyclerview.interfaces.showActivityListener

class MainActivity : AppCompatActivity(), showActivityListener{
    private  lateinit var binding: ActivityMainBinding
    private  val manager = supportFragmentManager
    private  val mainFragment =
        MainFragment()
    private lateinit var filePath : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val trans = manager.beginTransaction()
        if(!mainFragment.isAdded){
            trans.add(R.id.frameLayout, mainFragment).commit()
        }
    }

    override fun showActivity(activityType : String) {
        if (activityType == "showStudents"){
            val intent = Intent(this, showStudentsActivity::class.java)
            startActivity(intent)
        }
    }
}