package com.pineapple.budgetnotifier.database

import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense

import kotlinx.coroutines.flow.Flow


class BudgetRepository(
    db: BudgetNotifierDatabase,
) {

    val budgetDao = db.budgetDao()
    val expenseDao = db.expenseDao()

    var budgets: Flow<List<Budget>> = budgetDao.getAllBudgets()
    var expenses: Flow<List<Expense>> = expenseDao.getAllExpenses()

    suspend fun addOrUpdateBudget(budget: Budget) {
	budgetDao.insertOrReplaceBudgets(budget)
    }

    suspend fun addOrUpdateExpense(expense: Expense) {
	expenseDao.insertOrReplaceExpenses(expense)
    }
}
