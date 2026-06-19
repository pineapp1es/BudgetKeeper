package com.pineapple.budgetnotifier.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pineapple.budgetnotifier.utils.getRandomString
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
    @PrimaryKey val id: String,
    val budgetId: String,

    val name: String,
    val reason: String,
    val cost: Double,
    val date: Date,
) {
    companion object {
	fun newExpense(budgetId: String): Expense {
	    return Expense(
		id=budgetId+"-"+getRandomString(EXPENSEIDLENGTH),
		budgetId=budgetId,
		name="new-expense",
		reason="expense-reason",
		cost=0.0,
		date=Date(),
	    )
	}
    }
}
