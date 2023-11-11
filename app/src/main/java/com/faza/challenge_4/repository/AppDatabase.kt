package com.faza.challenge_4.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faza.challenge_4.model.Cart

@Database(entities = [Cart:: class], version = 1)
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
