package com.faza.challenge_4.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.faza.challenge_4.R
import com.faza.challenge_4.databinding.ActivityLoginBinding
import com.faza.challenge_4.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var tvRedirectSignUp: TextView
    lateinit var etUsername: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // View Binding
        tvRedirectSignUp = findViewById(R.id.regText)
        btnLogin = findViewById(R.id.btnLogin)
        etUsername = findViewById(R.id.et_username)
        etPass = findViewById(R.id.et_password)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }

        tvRedirectSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {
        val username = etUsername.text.toString()
        val pass = etPass.text.toString()
        auth.signInWithEmailAndPassword(username, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                navigateToMain()
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }
    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}