package com.faza.challenge_4.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.faza.challenge_4.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener{
            login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }
    }

    private fun register() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun login(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Login Berhasil", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Gagal Login, harap register terlebih dahulu", Toast.LENGTH_SHORT).show()
                }
            }
    }
}