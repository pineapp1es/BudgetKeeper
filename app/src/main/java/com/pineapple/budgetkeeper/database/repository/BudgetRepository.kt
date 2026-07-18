package com.pineapple.budgetkeeper.database.repository

import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.database.entities.Budget

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import androidx.room.Transaction


class BudgetRepository(
    db: BudgetNotifierDatabase,
) {
    val budgetDao = db.budgetDao()
    val budgets: Flow<List<Budget>> = budgetDao.getAllBudgets()

    @Transaction
    suspend fun addOrUpdateBudget(budget: Budget) {
	budgetDao.insertOrReplaceBudgets(budget)
    }

    @Transaction
    suspend fun deleteBudget(budget: Budget, moveExpensesTo: Budget? = null) {
	budgetDao.deleteBudgets(budget)
    }
}
