package com.pineapple.budgetnotifier.data

import java.time.LocalDate
import java.time.LocalTime


data class Expense(
    val budgetIds: MutableList<String>,
    val itemName: String,
    val spentAmount: Double,
    val time: LocalTime,
    val date: LocalDate,
    val desc: String
)