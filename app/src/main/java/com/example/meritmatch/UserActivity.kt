package com.example.meritmatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var skillsEditText: EditText
    private lateinit var linkedinInstagramEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        nameEditText = findViewById(R.id.name)
        emailEditText = findViewById(R.id.email)
        phoneEditText = findViewById(R.id.phone)
        skillsEditText = findViewById(R.id.skills)
        linkedinInstagramEditText = findViewById(R.id.linkedinInstagram)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            saveUserDetails()
            navigateToTasksActivity()
        }
    }

    private fun saveUserDetails() {
        val sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("name", nameEditText.text.toString())
        editor.putString("email", emailEditText.text.toString())
        editor.putString("phone", phoneEditText.text.toString())
        editor.putString("skills", skillsEditText.text.toString())
        editor.putString("linkedinInstagram", linkedinInstagramEditText.text.toString())
        editor.apply()
    }

    private fun navigateToTasksActivity() {
        val intent = Intent(this, TasksActivity::class.java)
        startActivity(intent)
    }
}