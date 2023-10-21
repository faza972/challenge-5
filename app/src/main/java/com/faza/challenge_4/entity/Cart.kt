package com.faza.challenge_4.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "CartOrder")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,
    @ColumnInfo (name = "img_id")
    var imgid: Int,
    @ColumnInfo(name = "food_name")
    var foodName: String? = null,
    @ColumnInfo(name = "quantity")
    var quantity: Int,
    @ColumnInfo(name = "food_price")
    var foodPrice: Int,
    @ColumnInfo(name = "total_all")
    var totalAll : Int,
    @ColumnInfo(name = "order_desk")
    var orderDesk: String? = null
): Parcelable
{
    companion object{
        const val TABLE_NAME = "cartOrder"
    }
}