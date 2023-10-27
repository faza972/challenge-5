package com.faza.challenge_4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faza.challenge_4.R
import com.faza.challenge_4.adapter.CartAdapter
import com.faza.challenge_4.databinding.FragmentCartBinding
import com.faza.challenge_4.viewModel.CartViewModel

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        setUpCartViewModel()

        cartAdapter = CartAdapter(cartViewModel, false)
        binding.rvCart.setHasFixedSize(true)
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = cartAdapter

        cartViewModel.allOrder.observe(viewLifecycleOwner) {
            cartAdapter.setData(it)

            var total = 0
            it.forEach { item ->
                total += item.totalAll
            }
            val totalPrice = "Rp. $total"
            binding.etTotalharga.text = totalPrice
        }
        addToConfirm()
        return binding.root
    }

    private fun addToConfirm() {
        binding.btnPesan.setOnClickListener{
            findNavController().navigate(R.id.action_cartFragment_to_orderFragment)
        }
    }

    private fun setUpCartViewModel() {
        val viewModelFactory = ViewModelFactory(requireActivity().application)
        cartViewModel = ViewModelProvider (this, viewModelFactory) [CartViewModel::class.java]
    }
}