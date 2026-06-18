package com.pineapple.budgetnotifier.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import kotlin.arrayOf

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Budget::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("budgetId"),
        )
    ],
)
data class Expense(
    @PrimaryKey val id: String,
    val budgetId: String,

    val name: String,
    val reason: String,
    val cost: String,
    val date: Date,
)
