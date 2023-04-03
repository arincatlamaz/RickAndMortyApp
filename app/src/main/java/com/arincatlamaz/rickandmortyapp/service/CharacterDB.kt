package com.arincatlamaz.rickandmortyapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(arrayOf(Character::class), arrayOf(Character::class),1)
abstract class CharacterDB : RoomDatabase() {

    /*abstract fun characterDao() : CharacterDao

    companion object{
        @Volatile private var instance: CharacterDB? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CharacterDB::class.java, "characterdatabase"
        ).build()

    }*/
}