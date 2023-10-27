package com.faza.challenge_4.data

import com.faza.challenge_4.R
import com.faza.challenge_4.model.Menu

interface MenuDataSource {

    fun getMenuData() : List<Menu>
}
class MenuDataImpl() : MenuDataSource {
    override fun getMenuData(): List<Menu> = listOf(
        Menu(
            name = "Ayam Bakar",
            price = 50000,
            image = R.drawable.shop_location,
            desc = "Ayam bakar dengan olesan madu dan daging empuk"
        ),
        Menu(
            name = "Ayam Goreng",
            price = 40000,
            image = R.drawable.baseline_account_circle_24,
            desc = "Ayam Goreng dengan tekstur renyah dan kenyal"
        ),
        Menu(
            name = "Ayam Geprek",
            price = 40000,
            image = R.drawable.icon_left,
            desc = "Ayam geprek dengan sambel matah yang menggugah selera"
        ),
        Menu(
            name = "Sate usus Ayam",
            price = 5000,
            image = R.drawable.baseline_account_circle_24,
            desc = "Sate usus untuk menemani makan mu tiga kali sehari, Seven days a week!!!"
        )
    )
}