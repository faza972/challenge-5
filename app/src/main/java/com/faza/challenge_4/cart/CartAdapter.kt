package com.faza.challenge_4.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faza.challenge_4.databinding.ListCartBinding
import com.faza.challenge_4.entity.Cart
import com.faza.challenge_4.viewModel.CartViewModel

class CartAdapter(private val cartViewModel: CartViewModel, private val cartInc: Boolean) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var cartItem: List<Cart> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ListCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItem.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItem[position]
        holder.bind(item, viewModel = cartViewModel, cartInc)
    }

    inner class CartViewHolder(private val binding: ListCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemCart: Cart, viewModel: CartViewModel, cartInc: Boolean) {
            // Set data itemCart ke tampilan dalam binding
            binding.tvNamaMenu.text = itemCart.foodName
            binding.ivGambar.setImageResource(itemCart.imgid)
            binding.tvHarga.text = itemCart.foodPrice.toString()
            binding.tvCountChart.text = itemCart.quantity.toString()

            // Menangani klik pada tombol 'Hapus'
            binding.ivDelete.setOnClickListener {
                viewModel.deleteItem(itemCart.id)
            }

            // Menangani klik pada tombol 'Kurang'
            binding.ivPlus.setOnClickListener {
                viewModel.decrement(itemCart)
                updateQuantity(itemCart.quantity)
            }

            // Menangani klik pada tombol 'Tambah'
            binding.ivMin.setOnClickListener {
                viewModel.increment(itemCart)
                updateQuantity(itemCart.quantity)
            }

            if (cartInc) {
                // Tampilkan elemen tambahan saat 'cartInc' adalah true
                binding.ivDelete.visibility = View.VISIBLE
                binding.ivPlus.visibility = View.VISIBLE
                binding.ivMin.visibility = View.VISIBLE
            } else {
                // Sembunyikan elemen tambahan
                binding.ivDelete.visibility = View.GONE
                binding.ivPlus.visibility = View.GONE
                binding.ivMin.visibility = View.GONE
            }
        }

        private fun updateQuantity(quantity: Int) {
            binding.tvCountChart.text = quantity.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cartItem: List<Cart>) {
        this.cartItem = cartItem
        notifyDataSetChanged()
    }
}