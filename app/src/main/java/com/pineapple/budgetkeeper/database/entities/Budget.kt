package com.pineapple.budgetkeeper.database.entities

import com.pineapple.budgetkeeper.utils.getRandomString

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Budget(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    val name: String,
    val desc: String,
    val limit: Double,
    val spent: Double,
    val startDate: Date,
    val endDate: Date,

) {
    constructor(budget: Budget, spent: Double):
	this(
	    budget.id,
	    budget.name,
	    budget.desc,
	    budget.limit,
	    spent,
	    budget.startDate,
	    budget.endDate,
	)

    companion object {

	fun newBudget(id: Long = 0): Budget {
	    return Budget(
		id = id,
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
