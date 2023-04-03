package com.arincatlamaz.rickandmortyapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arincatlamaz.rickandmortyapp.model.Character

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertAll(vararg character: Character) : List<Long>

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters() : List<Character>

    @Query("SELECT * FROM character WHERE uuid = :characterId")
    suspend fun getCharacter(characterId: Int) : Character

    @Query("DELETE FROM character")
    suspend fun deleteAllCharacters()

}