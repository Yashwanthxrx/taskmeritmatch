package com.example.meritmatch

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profilePhone: TextView
    private lateinit var profileSkills: TextView
    private lateinit var profileLinkedinInstagram: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileName = findViewById(R.id.name)
        profileEmail = findViewById(R.id.email)
        profilePhone = findViewById(R.id.phone)
        profileSkills = findViewById(R.id.skills)
        profileLinkedinInstagram = findViewById(R.id.linkedinInstagram)

        // Retrieve user profile details
        val sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")
        val skills = sharedPreferences.getString("skills", "")
        val linkedinInstagram = sharedPreferences.getString("linkedinInstagram", "")

        // Set user profile details
        profileName.text = "Name: $name"
        profileEmail.text = "Email: $email"
        profilePhone.text = "Phone: $phone"
        profileSkills.text = "Skills: $skills"
        profileLinkedinInstagram.text = "LinkedIn/Instagram: $linkedinInstagram"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()  // This will finish the current activity and go back to the previous one
    }
}