package com.arincatlamaz.rickandmortyapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Character (
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status : String,
    @SerializedName("species")
    var species: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("origin")
    var origin : LocationData,
    @SerializedName("location")
    var location : LocationData,
    @SerializedName("image")
    var image : String,
    @SerializedName("episode")
    var episode : List<String>,

    var favBtn: String?
): Parcelable