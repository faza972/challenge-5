package com.faza.challenge_4.repository

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1to2: Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE cart_menu ADD COLUMN foodQuantity INTEGER NOT NULL DEFAULT 1")
    }

}