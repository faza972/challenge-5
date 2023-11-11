package com.faza.challenge_4.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.faza.challenge_4.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    lateinit var tvRedirectLogin: TextView

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // View Bindings
        etEmail = findViewById(R.id.et_email)
        etPass = findViewById(R.id.et_password)
        btnSignUp = findViewById(R.id.btnReg)
        tvRedirectLogin = findViewById(R.id.backtlogin)

        // Initialising auth object
        auth = Firebase.auth

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}