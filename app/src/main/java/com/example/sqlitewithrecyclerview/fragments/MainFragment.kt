package com.example.sqlitewithrecyclerview.fragments

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sqlitewithrecyclerview.database.DbManager
import com.example.sqlitewithrecyclerview.R
import com.example.sqlitewithrecyclerview.activities.showStudentsActivity
import com.example.sqlitewithrecyclerview.classes.Student
import com.example.sqlitewithrecyclerview.databinding.FragmentMainBinding
import com.example.sqlitewithrecyclerview.interfaces.showActivityListener
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private  lateinit var bind: FragmentMainBinding
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var filePath : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        bind = FragmentMainBinding.bind(view)
        val importBtn = bind.importBtn
        val addBtn = bind.addBtn
        val showBtn = bind.showBtn

        importBtn.setOnClickListener {
            fileChooser()
        }
         addBtn.setOnClickListener {
             save()
         }
        showBtn.setOnClickListener {
            (context as showActivityListener).showActivity("showStudents")
        }
        return view;
    }

    private fun fileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Veuillez choisir une image"), 2021)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2021 && resultCode == Activity.RESULT_OK && data != null){
            filePath = data.data!!
            var map = MediaStore.Images.Media.getBitmap(activity?.contentResolver, filePath)
            bind.imageView.setImageBitmap(map)
        }
    }

    private  fun save(){

        if (!this::filePath.isInitialized){
            Toast.makeText(context, "L'image est obligatoire", Toast.LENGTH_LONG).show()
            return
        }

        val nom = bind.editTextNom.text.toString()
        val prenom = bind.editTextPrenom.text.toString()
        val age = bind.editTextAge.text.toString()

        if (nom.isEmpty()){
            Toast.makeText(context, "Le champ du nom est obligatoire", Toast.LENGTH_LONG).show()
            return
        }

        if (prenom.isEmpty()){
            Toast.makeText(context, "Le champ du prenom est obligatoire", Toast.LENGTH_LONG).show()
            return
        }

        if (age.isEmpty()){
            Toast.makeText(context, "Le champ de l'age est obligatoire", Toast.LENGTH_LONG).show()
            return
        }else{
            try {
                Integer.parseInt(age)
            }catch (e : Exception){
                return
            }
        }

        //copy image to local directory

        // adding timestamp to the name in order to ensure a unique name for the image
        var timestamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val internalImagePath = copyImage("$timestamp$nom-$prenom.png")

        if (internalImagePath != null) {
            Log.i("mohamed", internalImagePath)
        }else{
            Log.i("mohamed", "image not saved")
            return
        }

        val dbManager = context?.let {
            DbManager(
                it
            )
        }
        internalImagePath?.let {
            dbManager?.add(
                Student(
                    0,
                    nom,
                    prenom,
                    Integer.parseInt(age),
                    internalImagePath
                )
            )
        }

        //
        bind.editTextNom.text.clear()
        bind.editTextPrenom.text.clear()
        bind.editTextAge.text.clear()
        bind.imageView.setImageResource(R.drawable.ic_baseline_account_circle_24)

    }

    val localImagesDirectory = "Images"
    private fun copyImage(distName : String) : String?{

        var imageBitMap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, filePath)
        val cw = ContextWrapper(context)
        val directory = cw.getDir(localImagesDirectory, Context.MODE_PRIVATE)
        val distImage = File(directory, distName)

        try {

            val outStream = FileOutputStream(distImage)
            imageBitMap.compress(CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()

        }catch (e : Exception){
            Log.i("mohamed", "error copying the image")
            return null
        }

        return distImage.absolutePath
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}