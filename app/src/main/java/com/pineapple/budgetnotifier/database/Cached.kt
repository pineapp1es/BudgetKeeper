package com.pineapple.budgetnotifier.database

import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase.Companion.getDb
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense
import java.util.Date

data object Selected {
    var budget: Budget? = null
    var expense: Expense? = null
}

data object Cached {
    val budgets: MutableList<Budget> = mutableListOf()
    val expenses: MutableList<Expense> = mutableListOf()
}

fun getCollectiveBudget(): Budget {

    var collectiveLimit = 0.0
    var collectiveSpent = 0.0
    var collectiveStartDate = Date()
    var collectiveEndDate = Date()

    cacheAllBudgetData()

    for (budget in Cached.budgets) {
	collectiveLimit += budget.limit
	collectiveSpent += budget.spent
	if (collectiveStartDate.time > budget.startDate.time)
	    collectiveStartDate = budget.startDate
	if (collectiveEndDate.time < budget.endDate.time)
	    collectiveEndDate = budget.endDate
    }

    val collectiveBudget = Budget(
	id="collective-budget-id-tmp",
	name="collective budget",
	desc="all budgets bundled in one",
	limit=collectiveLimit,
	spent=collectiveSpent,
	startDate=collectiveStartDate,
	endDate=collectiveEndDate,
    )

    return collectiveBudget
}

fun cacheAllBudgetData() {
    val db = getDb()
    Cached.budgets.addAll(
	loadAllBudgets()
    )
}

fun cacheAllExpenseData() {

}
