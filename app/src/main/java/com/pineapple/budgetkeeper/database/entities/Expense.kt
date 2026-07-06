package com.pineapple.budgetkeeper.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pineapple.budgetkeeper.utils.getRandomString
import java.util.Date
import kotlin.arrayOf

const val EXPENSEIDLENGTH = 10

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
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val budgetId: Long,

    val name: String,
    val reason: String,
    val cost: Double,
    val date: Date,
) {
    companion object {
	fun newExpense(budgetId: Long = 0): Expense {
	    return Expense(
		budgetId=budgetId,
		name="new-expense",
		reason="expense-reason",
		cost=0.0,
		date=Date(),
	    )
	}
    }
}
