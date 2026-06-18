package com.pineapple.budgetnotifier.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Budget(
    @PrimaryKey val id: String,

    val name: String,
    val desc: String,
    val limit: Double,
    val spent: Double,
    val startDate: Date,
    val endDate: Date,
)
