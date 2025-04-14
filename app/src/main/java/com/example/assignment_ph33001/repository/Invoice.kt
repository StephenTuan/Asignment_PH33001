package com.example.assignment_ph33001.repository

import kotlin.random.Random

fun generateInvoiceId(): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return "#" + (1..6)
        .map { chars.random() }
        .joinToString("")
}