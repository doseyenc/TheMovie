package com.example.themovie.detail.data.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String?,

    @SerializedName("iso_639_1")
    val iso: String?,

    @SerializedName("name")
    val name: String?

)
