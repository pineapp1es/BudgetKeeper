package com.pineapple.budgetnotifier.database.entities

import com.pineapple.budgetnotifier.utils.getRandomString

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

) {
    companion object {

	const val DEFAULTBUDGETIDLEN: Int = 10

	fun newBudget(): Budget {
	    return Budget(
		id = getRandomString(DEFAULTBUDGETIDLEN),
		name= "new-budget",
		desc= "",
		limit= 0.0,
		spent= 0.0,
		startDate= Date(),
		endDate= Date(),
	    )
	}
    }
}
