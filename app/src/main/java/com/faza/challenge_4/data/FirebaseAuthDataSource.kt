package com.faza.challenge_4.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthDataSource {
    fun getCurrentUser(): FirebaseUser?
}
class FirebaseAuthDataSourceImpl(private val firebaseAuth: FirebaseAuth) : FirebaseAuthDataSource {
    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}