package com.pineapple.budgetnotifier.database

import android.content.Context

import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase.Companion.getDb
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense
import java.util.Date


data object Selected {
    var budget: Budget? = null
    var expense: Expense? = null
}

data object Cached {
    val budgetIds: MutableList<String> = mutableListOf()
    val budgets: MutableList<Budget> = mutableListOf()
    val expenses: MutableList<Expense> = mutableListOf()
}

suspend fun cacheAllBudgetData(applicationContext: Context) {
    val db = getDb(applicationContext)
    db.budgetDao().getAllBudgets().collect { budgets ->
	Cached.budgets.addAll(budgets)
    }
}

fun cacheAllExpenseData() {

}
