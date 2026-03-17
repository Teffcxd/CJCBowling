package com.example.cjcbowling.model

data class Reservation(
    val id: Int = 0,
    val clientName: String,
    val phone: String,
    val lane: Int,
    val date: String,
    val time: String,
    val status: String
)
