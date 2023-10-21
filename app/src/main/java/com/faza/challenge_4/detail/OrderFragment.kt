package com.faza.challenge_4.detail

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faza.challenge_4.R
import com.faza.challenge_4.cart.CartAdapter
import com.faza.challenge_4.databinding.FragmentOrderBinding
import com.faza.challenge_4.viewModel.CartViewModel
import com.faza.challenge_4.viewModel.DetailViewModel

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCartViewModel()
        getConfirmOrder()
        showOrder()
    }

    private fun showOrder() {
        val adapter = CartAdapter(cartViewModel, true)
        binding.rvOrderCart.adapter = adapter
        binding.rvOrderCart.layoutManager = LinearLayoutManager(requireContext())

        cartViewModel.allOrder.observe(viewLifecycleOwner) { cartItems ->
            adapter.setData(cartItems)
            var total = 0
            cartItems.forEach { item ->
                total += item.totalAll
            }
            val totalText = "Rp. $total"
            binding.totalPembayaran.text = totalText
        }
    }

    private fun getConfirmOrder() {
        cartViewModel.allOrder.observe(viewLifecycleOwner) { cartItems ->
            var totalSemua = 0
            cartItems.forEach { item ->
                totalSemua += item.totalAll
            }
            val totalText = "Rp. $totalSemua"
            binding.totalPembayaran.text = totalText
        }
    }

    private fun setCartViewModel() {
        val viewModelFactory = ViewModelFactory(requireActivity().application)
        cartViewModel = ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
    }
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

