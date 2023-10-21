package com.faza.challenge_4.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faza.challenge_4.entity.Cart

@Dao
interface CartDao {

    @Insert
    fun insertCart(cart: Cart)
    @Query("SELECT * FROM CARTORDER ORDER BY id")
    fun getAllCartOrder(): LiveData<List<Cart>>

    @Delete
    fun deleteCart(cart: Long): Int

    @Update
    fun updateCart(cart: Cart): Int


}