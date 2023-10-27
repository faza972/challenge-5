package com.faza.challenge_4.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faza.challenge_4.model.Cart
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CartRepo(application: Application) {
    private val mcartDao : CartDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = AppDatabase.getInstance(application)
        mcartDao = db.cartDao
    }
    fun getAllCartOrder(): LiveData<List<Cart>> = mcartDao.getAllCartOrder()
    fun insert(cart: Cart){
        executorService.execute { mcartDao.insertCart(cart) }
    }
    fun delete(cart: Long){
        executorService.execute { mcartDao.deleteCart(cart) }
    }
    fun update(cart: Cart){
        executorService.execute { mcartDao.updateCart(cart) }
    }
}
@Database (entities = [Cart:: class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val cartDao: CartDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase?= null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "cart_database"
                    )
                        .allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
