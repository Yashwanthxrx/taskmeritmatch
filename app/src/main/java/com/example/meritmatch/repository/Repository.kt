package com.example.meritmatch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meritmatch.Task
import com.example.meritmatch.model.*
import com.example.meritmatch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun signup(user: User): LiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        RetrofitClient.instance.signup(user).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // handle error
            }
        })
        return data
    }

    fun login(user: User): LiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        RetrofitClient.instance.login(user).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // handle error
            }
        })
        return data
    }

    fun createTask(task: Task, userId: Int): LiveData<TaskResponse> {
        val data = MutableLiveData<TaskResponse>()
        RetrofitClient.instance.createTask(task, userId).enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                // handle error
            }
        })
        return data
    }

    fun getTasks(skip: Int, limit: Int): LiveData<List<TaskResponse>> {
        val data = MutableLiveData<List<TaskResponse>>()
        RetrofitClient.instance.getTasks(skip, limit).enqueue(object : Callback<List<TaskResponse>> {
            override fun onResponse(call: Call<List<TaskResponse>>, response: Response<List<TaskResponse>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<TaskResponse>>, t: Throwable) {
                // handle error
            }
        })
        return data
    }
}