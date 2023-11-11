package com.faza.challenge_4.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faza.challenge_4.model.Cart

@Dao
interface CartDao {

    @Insert
    fun insertCart(cart: Cart)
    @Query("SELECT * FROM CARTORDER ORDER BY id")
    fun getAllCartOrder(): LiveData<List<Cart>>

    @Query("DELETE FROM CartOrder WHERE id = :cartId")
    fun deleteCart(cartId: Int): Int

    @Query("DELETE FROM CartOrder")
    fun deleteItems()

    @Delete
    fun delete(cart: Cart)

    @Update
    fun updateCart(cart: Cart)
    @Query("SELECT * FROM CartOrder WHERE food_name = :foodName")
    fun getItem(foodName: String): Cart?
    fun updateCartMenu(cart: Cart){
        val existingItem = cart.foodName?.let { getItem(it) }
        if (existingItem != null) {
            val newQuantity = existingItem.quantity + cart.quantity
            val TotalPrice = newQuantity * existingItem.foodPrice
            existingItem.quantity = newQuantity
            existingItem.totalAll = TotalPrice
            updateCart(existingItem)
        } else {
            insertCart(cart)
        }
    }

}