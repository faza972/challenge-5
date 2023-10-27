package com.faza.challenge_4.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faza.challenge_4.model.Cart
import com.faza.challenge_4.model.Menu
import com.faza.challenge_4.repository.CartRepo

class DetailViewModel (context: Application) : AndroidViewModel(context){
//    private var _menuCart: MutableLiveData<List<Menu>> = MutableLiveData(arrayListOf())
//    val menuCart : LiveData<List<Menu>> get() = _menuCart

    val counter = MutableLiveData(1)
    private val _allPrice  = MutableLiveData<Int>()
    val allPrice : LiveData<Int> = _allPrice
    private val _select = MutableLiveData<Menu>()
    private val cartRepo: CartRepo
    init {
        cartRepo = CartRepo(context)
    }

    private fun insert(cart: Cart){
        cartRepo.insert(cart)
    }

    fun initSelectItem(item: Menu){
        _select.value = item
        _allPrice.value = item.price
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
        val currentValue: Int = counter.value ?: 0
        counter.value = currentValue + 1
        total()
    }
    fun decrement() {
        val currentValue: Int = counter.value ?: 1
        if (currentValue > 1) {
            counter.value = currentValue - 1
            total()
        }
    }
    fun addToCart(){
        val selectedItem = _select.value

        selectedItem?.let { selectedItem ->
            allPrice.value?.let { allPrice ->
                counter.value?.let { counter ->
                    val cartItem = Cart(
                        foodName = selectedItem.name,
                        imgid = selectedItem.image,
                        foodPrice = selectedItem.price,
                        quantity = counter,
                        totalAll = allPrice,
                        orderDesk = ""
                    )
                    insert(cartItem)
                }
            }
        }
    }
}