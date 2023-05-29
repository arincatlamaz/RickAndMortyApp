package com.arincatlamaz.rickandmortyapp.util

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun addToFB (context: Context, position: Int, name: String) {

    val serial2 = hashMapOf(
        "serial" to getSerialNum(context),
        "favorite" to position,
        "name" to name
    )

    val db = Firebase.firestore
    val col = db.collection("users")

    col.document(getSerialNum(context))
        .set(serial2)
        .addOnSuccessListener {
            Log.d("SERIAL", "USER ADDED")
        }
        .addOnFailureListener{
            Log.w("SERIAL", "Error adding document")
        }
}


fun getFromFB(){

}


/*
fun addToFB (context: Context, position: Int, name: String) {

    val serial2 = hashMapOf(
        "serial" to getSerialNum(context),
        "favorite" to position,
        "name" to name
    )

    val db = Firebase.firestore
    val col = db.collection("users")

    col.document(getSerialNum(context))
        .set(serial2)
        .addOnSuccessListener {
            Log.d("SERIAL", "USER ADDED")
        }
        .addOnFailureListener{
            Log.w("SERIAL", "Error adding document")
        }
}*/
