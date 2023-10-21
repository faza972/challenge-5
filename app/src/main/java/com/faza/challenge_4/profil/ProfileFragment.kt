package com.faza.challenge_4.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.faza.challenge_4.data.UserDataSourceImpl
import com.faza.challenge_4.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
   private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProfile()
    }

    private fun setupProfile(){
        val user = UserDataSourceImpl().getUserData()
        binding.ivFoto.load(user.image){
            crossfade(true)
        }
        binding.etUsername.setText(user.username)
        binding.etEmail.setText(user.email)
        binding.etPassword.setText(user.password)
        binding.etContact.setText(user.password)
    }
}