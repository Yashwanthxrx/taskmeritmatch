package com.example.meritmatch

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ViewTasksActivity : AppCompatActivity() {

    private lateinit var tasksContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)

        tasksContainer = findViewById(R.id.tasksContainer)

        // Retrieve tasks from SharedPreferences
        val sharedPreferences = getSharedPreferences("Tasks", Context.MODE_PRIVATE)
        val tasksJson = sharedPreferences.getString("tasks", "")
        val type = object : TypeToken<MutableList<Task>>() {}.type
        val tasks: MutableList<Task> = Gson().fromJson(tasksJson, type) ?: mutableListOf()

        // Display tasks
        displayTasks(tasks)
    }

    private fun displayTasks(tasks: List<Task>) {
        tasksContainer.removeAllViews()

        for (task in tasks) {
            val taskView = layoutInflater.inflate(R.layout.task_item, tasksContainer, false)

            val taskTitle = taskView.findViewById<TextView>(R.id.taskTitle)
            val taskDescription = taskView.findViewById<TextView>(R.id.taskDescription)
            val taskPoints = taskView.findViewById<TextView>(R.id.taskPoints)
            val reserveButton = taskView.findViewById<Button>(R.id.reserveButton)

            taskTitle.text = task.title
            taskDescription.text = task.description
            taskPoints.text = task.points.toString()

            // Set reserve button state based on task reservation status
            if (task.isReserved) {
                reserveButton.text = "Reserved"
                reserveButton.isEnabled = false
            } else {
                reserveButton.text = "Reserve Task"
                reserveButton.isEnabled = true
            }

            // Set reserve button click listener
            reserveButton.setOnClickListener {
                task.isReserved = true
                reserveButton.text = "Reserved"
                reserveButton.isEnabled = false

                // Save the updated tasks back to SharedPreferences
                saveTasks(tasks)

                // Notify user
                Toast.makeText(this, "${task.title} reserved!", Toast.LENGTH_SHORT).show()
            }

            tasksContainer.addView(taskView)
        }
    }

    private fun saveTasks(tasks: List<Task>) {
        val sharedPreferences = getSharedPreferences("Tasks", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val tasksJson = Gson().toJson(tasks)
        editor.putString("tasks", tasksJson)
        editor.apply()
    }
}