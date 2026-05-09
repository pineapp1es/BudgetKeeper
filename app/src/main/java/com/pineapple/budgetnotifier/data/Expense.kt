package com.pineapple.budgetnotifier.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime


data class Expense(
    val budgetIds: MutableList<String>,
    val itemName: String,
    val spentAmount: Double,
    val time: LocalTime,
    val date: LocalDate,
    val desc: String
) {
    companion object Factory {
        @RequiresApi(Build.VERSION_CODES.O)
        fun createNewEmpty(): Expense {
            return Expense(
                mutableListOf(),
                "New Expense",
                0.0,
                LocalTime.now(),
                LocalDate.now(),
                "",
            )
        }
    }
}
