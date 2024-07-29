package com.example.meritmatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TasksActivity : AppCompatActivity() {

    private lateinit var profileIcon: ImageView
    private lateinit var karmaPoints: TextView
    private lateinit var createTaskButton: Button
    private lateinit var viewTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        profileIcon = findViewById(R.id.profileIcon)
        karmaPoints = findViewById(R.id.karmaPoints)
        createTaskButton = findViewById(R.id.createTaskButton)
        viewTaskButton = findViewById(R.id.viewTaskButton)

        profileIcon.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        createTaskButton.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivity(intent)
        }

        viewTaskButton.setOnClickListener {
            val intent = Intent(this, ViewTasksActivity::class.java)
            startActivity(intent)
        }

        // Retrieve and display karma points from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val karma = sharedPreferences.getInt("karma", 0)
        karmaPoints.text = karma.toString()
    }
}