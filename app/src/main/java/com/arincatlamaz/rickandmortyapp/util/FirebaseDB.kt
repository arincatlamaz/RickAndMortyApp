package com.arincatlamaz.rickandmortyapp.util

import android.content.Context
import android.util.Log
import android.widget.ImageButton
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun addToFB(context: Context, position: Int, name: String, favoriteBtn: ImageButton) {
    val db = Firebase.firestore
    val docRef = db.collection("users").document(getSerialNum(context))

    docRef.get().addOnSuccessListener { documentSnapshot ->
        val favoriteList = documentSnapshot.toObject(User::class.java)?.favoriteList

        if (favoriteList != null) {
            val newItem = "Position: $position - Name: $name"
            if (favoriteList.contains(newItem)) {
                favoriteList.remove(newItem)
                docRef.set(User(favoriteList))
                    .addOnSuccessListener {
                        Log.d("SERIAL", "Favorite removed")
                        favoriteBtn.setBackgroundResource(R.drawable.favorite_gray)
                    }
                    .addOnFailureListener { exception ->
                        Log.w("SERIAL", "Error removing favorite: ${exception.message}")
                    }
            } else {
                favoriteList.add(newItem)
                docRef.set(User(favoriteList))
                    .addOnSuccessListener {
                        Log.d("SERIAL", "Favorite added")
                        favoriteBtn.setBackgroundResource(R.drawable.favorite_red)
                    }
                    .addOnFailureListener { exception ->
                        Log.w("SERIAL", "Error adding favorite: ${exception.message}")
                    }
            }
        } else {
            val newFavoriteList = arrayListOf("Position: $position - Name: $name")
            docRef.set(User(newFavoriteList))
                .addOnSuccessListener {
                    Log.d("SERIAL", "Favorite added")
                    favoriteBtn.setBackgroundResource(R.drawable.favorite_red)
                }
                .addOnFailureListener { exception ->
                    Log.w("SERIAL", "Error adding favorite: ${exception.message}")
                }
        }
    }
}

