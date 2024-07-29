package com.example.meritmatch

data class Task(
    val title: String,
    val description: String,
    val points: Int,
    var isReserved: Boolean = false

)