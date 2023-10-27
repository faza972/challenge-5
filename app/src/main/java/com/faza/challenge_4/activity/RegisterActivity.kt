package com.faza.challenge_4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.faza.challenge_4.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnReg.setOnClickListener{
            signUp(binding.etUsername.text.toString(), binding.etPassword.text.toString(), binding.etUsername.text.toString(), binding
                .etPhoneNumber.text.toString())
        }
    }

    private fun signUp(email: String, password: String, username: String, noTelp: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User registration successful
                    val user = auth.currentUser

                    // Now, update the user's profile with additional information
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(username) // Set the username
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { profileUpdateTask ->
                            if (profileUpdateTask.isSuccessful) {
                                // Profile update successful, save phone number to database
                                savePhoneNumberToDatabase(user.uid, noTelp)
                            }
                        }
                } else {
                    val exception = task.exception
                    if (exception != null) {
                        println("Error during user registration: ${exception.message}")
                        // Handle the registration failure, e.g., show an error message to the user
                    }
                }
            }
    }

    private fun savePhoneNumberToDatabase(userId: String, phoneNumber: String) {
        // Initialize Firebase Realtime Database
        val database = FirebaseDatabase.getInstance()
        val usersRef: DatabaseReference = database.getReference("users")

        // Create a child node for the user and save their phone number
        val userRef = usersRef.child(userId)

        // Assuming a structure like this: users -> userID -> phoneNumber
        userRef.child("phoneNumber").setValue(phoneNumber)
    }

}