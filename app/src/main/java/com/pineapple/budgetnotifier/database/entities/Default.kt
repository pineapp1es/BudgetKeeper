package com.pineapple.budgetnotifier.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Budget::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("budgetId"),
        )
    ],
)
data class Default(
    val budgetId: String
)
