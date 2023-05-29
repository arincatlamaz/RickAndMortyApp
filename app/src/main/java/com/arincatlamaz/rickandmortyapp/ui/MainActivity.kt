package com.arincatlamaz.rickandmortyapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.util.addToFB
import com.arincatlamaz.rickandmortyapp.util.getSerialNum
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RickAndMortyApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)
        setupActionBarWithNavController(navController!!)

//        addToFB(this@MainActivity)

        val db = Firebase.firestore

        val data = db.collection("users").document(getSerialNum(this@MainActivity))

        data.get()
            .addOnSuccessListener { document ->
                Log.d("FBDATA",document.data?.getValue("name").toString())
            }

            .addOnFailureListener { exception ->
                Log.d("FBDATA", "get failed with ", exception)
            }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp() || super.onSupportNavigateUp()
    }

}