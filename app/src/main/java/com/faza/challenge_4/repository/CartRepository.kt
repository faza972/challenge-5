package com.faza.challenge_4.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.faza.challenge_4.model.Cart
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CartRepository(application: Application) {
    private val mcartDao : CartDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = AppDatabase.getInstance(application)
        mcartDao = db.cartDao
    }

    fun addCart(cart: Cart){
        executorService.execute {
            mcartDao.updateCart(cart)
        }
    }
    fun getAllCartOrder(): LiveData<List<Cart>> = mcartDao.getAllCartOrder()
    fun insert(cart: Cart){
        executorService.execute { mcartDao.insertCart(cart) }
    }
    fun deleteItem(){
        executorService.execute { mcartDao.deleteItems() }
    }
    fun deleteCart(cartId: Int){
        executorService.execute { mcartDao.deleteCart(cartId) }
    }
    fun update(cart: Cart){
        executorService.execute { mcartDao.updateCart(cart) }
    }
}
