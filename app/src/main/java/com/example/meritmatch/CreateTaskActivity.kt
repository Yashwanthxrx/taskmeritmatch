package com.example.meritmatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var taskTitle: EditText
    private lateinit var taskDescription: EditText
    private lateinit var taskPoints: EditText
    private lateinit var saveTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        taskTitle = findViewById(R.id.taskTitle)
        taskDescription = findViewById(R.id.taskDescription)
        taskPoints = findViewById(R.id.taskPoints)
        saveTaskButton = findViewById(R.id.saveTaskButton)

        saveTaskButton.setOnClickListener {
            saveTask()
        }
    }

    private fun saveTask() {
        // Retrieve task details
        val title = taskTitle.text.toString()
        val description = taskDescription.text.toString()
        val points = taskPoints.text.toString().toInt()

        // Save task to SharedPreferences
        val sharedPreferences = getSharedPreferences("Tasks", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()

        // Retrieve existing tasks
        val tasksJson = sharedPreferences.getString("tasks", "")
        val type = object : TypeToken<MutableList<Task>>() {}.type
        val tasks: MutableList<Task> = gson.fromJson(tasksJson, type) ?: mutableListOf()

        // Add new task
        tasks.add(Task(title, description, points))

        // Save updated tasks list
        editor.putString("tasks", gson.toJson(tasks))
        editor.apply()

        // Provide feedback to the user
        Toast.makeText(this, "Task saved successfully!", Toast.LENGTH_SHORT).show()

        // Clear the input fields
        taskTitle.text.clear()
        taskDescription.text.clear()
        taskPoints.text.clear()

        // Navigate back to TasksActivity
        val intent = Intent(this, TasksActivity::class.java)
        startActivity(intent)
        finish()
    }
}

