package com.pineapple.budgetkeeper.database.repository

import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.database.entities.Expense

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import androidx.room.Transaction


class ExpenseRepository(
    db: BudgetNotifierDatabase,
) {
    val expenseDao = db.expenseDao()
    val expenses: Flow<List<Expense>> = expenseDao.getAllExpenses()

    suspend fun addOrUpdateExpense(expense: Expense) {
	expenseDao.insertOrReplaceExpenses(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
	expenseDao.deleteExpenses(expense);
    }

    suspend fun moveExpenseTo(expenseId: Long, budgetId: Long) {
	expenseDao.moveExpenseTo(expenseId, budgetId)
    }

    suspend fun moveExpensesFromOldToNewBudget(oldBudgetId: Long, newBudgetId: Long) {
        expenseDao.moveExpensesFromOldToNewBudget(oldBudgetId, newBudgetId)
    }

    suspend fun getBudgetSpent(budgetId: Long): Double {
        return expenseDao.getTotalSpentByBudget(budgetId)
    }
}
