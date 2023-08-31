package com.example.landmarkpay.database

data class Recipient(
    val id: Int,
    val recipientAccountNumber: String,
    val recipientName: String,
    val country: String,
    val bic: String
)
