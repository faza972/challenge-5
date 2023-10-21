package com.faza.challenge_4.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.faza.challenge_4.entity.Cart
import com.faza.challenge_4.repository.CartRepo


class CartViewModel (application: Application) : ViewModel(){
    private val repo: CartRepo = CartRepo(application)

    val allOrder: LiveData<List<Cart>> = repo.getAllCartOrder()

    fun deleteItem(idCart: Long?) {
        if (idCart != null) {
            repo.delete(idCart)
        }
    }

    private fun updateQuantity (cart: Cart) {
        repo.update(cart)
    }

    @SuppressLint("SuspiciousIndentation")
    fun increment(cart: Cart){
        val total = cart.quantity + 1
        cart.totalAll = cart.foodPrice * total
        updateQuantity(cart)
    }

    fun decrement(cart: Cart) {
        val total = cart.quantity - 1
        cart.totalAll = cart.foodPrice * total
        updateQuantity(cart)
    }
}