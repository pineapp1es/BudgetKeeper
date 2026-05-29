package com.pineapple.budgetnotifier.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time
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
    val time: Time,
)
