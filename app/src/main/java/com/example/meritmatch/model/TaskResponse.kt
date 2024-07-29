package com.example.meritmatch.model

data class TaskResponse(
    val id: Int,
    val title: String,
    val description: String,
    val karma_points: Int,
    val owner_id: Int
)