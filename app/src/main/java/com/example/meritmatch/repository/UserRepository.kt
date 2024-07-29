package com.example.meritmatch.repository

import com.example.meritmatch.model.User

class UserRepository {

    fun signup(user: User, callback: (User?) -> Unit) {
        // Implement your backend communication here
        // For example, using Retrofit to make a network request
        // Simulate success response
        user.karmaPoints = 100 // Give initial karma points
        callback(user)
    }

    fun login(user: User, callback: (User?) -> Unit) {
        // Implement your backend communication here
        // For example, using Retrofit to make a network request
        // Simulate success response
        callback(user)
    }
}