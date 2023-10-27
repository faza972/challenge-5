package com.faza.challenge_4.model

data class OrderReq(
    val orders: List<OrderConfirm?>?,
    val total: Int?,
    val username: String?
)
