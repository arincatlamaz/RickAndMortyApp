package com.arincatlamaz.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDataFromAPI()

    }


    private fun getDataFromAPI(){

        RickAndMortyAPIService().getData().enqueue(object : Callback<GetCharacterResponse> {
            override fun onResponse(call: Call<GetCharacterResponse>, response: Response<GetCharacterResponse>) {
                Log.i("MainActivity", response.toString())
                if (!response.isSuccessful){
                    Toast.makeText(this@MainActivity,"Unsuccesfull Network Call", Toast.LENGTH_LONG)
                    return
                }
                val body = response.body()!!
                val name = body.name
                val textView = findViewById<TextView>(R.id.nametextview)
                textView.text = name
            }

            override fun onFailure(call: Call<GetCharacterResponse>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null Message")
            }

        })
    }
}