package com.faza.challenge_4.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faza.challenge_4.model.Cart
import com.faza.challenge_4.model.Menu
import com.faza.challenge_4.repository.AppDatabase
import com.faza.challenge_4.repository.CartDao
import com.faza.challenge_4.repository.CartRepository

class DetailViewModel (application: Application) :ViewModel(){
    private val _counter = MutableLiveData(1)
    val counter: LiveData<Int> = _counter

    private val _allPrice  = MutableLiveData<Int>()
    val allPrice : LiveData<Int> = _allPrice

    private val _select = MutableLiveData<Menu>()
    private val cartDao: CartDao

    private val cartRepo: CartRepository = CartRepository(application)

    init {
        val database = AppDatabase.getInstance(application)
        cartDao = database.cartDao
    }

    private fun insert(cart: Cart){
        cartRepo.insert(cart)
    }

    private fun total(){
        val currencyAmount = counter.value?:1
        val selectItem = _select.value
        if (selectItem!= null){
            val totalPrice = selectItem.price*currencyAmount
            _allPrice.value = totalPrice
        }
    }

    fun increment() {
        val currentValue: Int = counter.value ?: 1
        _counter.value = currentValue + 1
        total()
    }
    fun decrement() {
        val currentValue: Int = counter.value ?: 1
        if (currentValue > 1) {
            _counter.value = currentValue - 1
            total()
        }
    }
    fun initSelectedItem(item: Menu) {
       _select.value =item
        _allPrice.value = item.price

    }
    fun addToCart(){
        val selectedItem = _select.value

        selectedItem?.let {
            val cartItem =
                allPrice.value?.let { it1 ->
                    counter.value?.let { it2 ->
                        Cart(
                            foodName = it.name,
                            imgid = it.image,
                            foodPrice = it.price,
                            quantity = it2,
                            totalAll = it1,
                            orderDesk = ""
                        )
                    }
                }
            cartRepo.addCart(cartItem!!)
        }
    }
}