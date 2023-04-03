package com.arincatlamaz.rickandmortyapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Character (
    @ColumnInfo("id")
    @SerializedName("id")
    var id : Int,
    @ColumnInfo("name")
    @SerializedName("name")
    var name: String,
    @ColumnInfo("status")
    @SerializedName("status")
    var status : String,
    @ColumnInfo("species")
    @SerializedName("species")
    var species: String,
    @ColumnInfo("gender")
    @SerializedName("gender")
    var gender: String,
    @ColumnInfo("origin")
    @SerializedName("origin")
    var origin : LocationData,
    @ColumnInfo("location")
    @SerializedName("location")
    var location : LocationData,
    @ColumnInfo("image")
    @SerializedName("image")
    var image : String,
    @ColumnInfo("episode")
    @SerializedName("episode")
    var episode : List<String>,

    var favBtn: String?
): Parcelable{
    @PrimaryKey(autoGenerate = true)
    val uuid: Int = 0
}