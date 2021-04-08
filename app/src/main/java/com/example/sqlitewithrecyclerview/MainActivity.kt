package com.example.sqlitewithrecyclerview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.sqlitewithrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private  val manager = supportFragmentManager
    private  val mainFragment = MainFragment()
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
}