package com.example.meritmatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meritmatch.model.User
import com.example.meritmatch.repository.UserRepository

class MainViewModel : ViewModel() {
    private val userRepository = UserRepository()

    fun signup(user: User): LiveData<User?> {
        val userLiveData = MutableLiveData<User?>()
        userRepository.signup(user) { userResponse ->
            userLiveData.postValue(userResponse)
        }
        return userLiveData
    }

    fun login(user: User): LiveData<User?> {
        val userLiveData = MutableLiveData<User?>()
        userRepository.login(user) { userResponse ->
            userLiveData.postValue(userResponse)
        }
        return userLiveData
    }
}