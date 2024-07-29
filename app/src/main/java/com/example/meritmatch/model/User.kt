package com.example.meritmatch.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    var username: String,
    @SerializedName("karma_points") var karmaPoints: Int
)

