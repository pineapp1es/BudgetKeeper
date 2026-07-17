package com.pineapple.budgetkeeper.database

import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.database.entities.Expense

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import androidx.room.Transaction


class BudgetRepository(
    db: BudgetNotifierDatabase,
) {

    val budgetDao = db.budgetDao()
    val expenseDao = db.expenseDao()

    var budgets: Flow<List<Budget>> = budgetDao.getAllBudgets()
    var expenses: Flow<List<Expense>> = expenseDao.getAllExpenses()

    @Transaction
    suspend fun correctAllBudgets() {
	val budgetList = budgets.first()
	for (budget in budgetList) {
	    val correctSpent: Double = expenseDao.getTotalSpentByBudget(budget.id)
	    if (budget.spent != correctSpent)
		budgetDao.changeSpent(budget.id, correctSpent - budget.spent)
	}
    }

    @Transaction
    suspend fun addOrUpdateBudget(budget: Budget) {
	budgetDao.insertOrReplaceBudgets(budget)
    }

    @Transaction
    suspend fun addOrUpdateExpense(expense: Expense) {
	val oldExpense = expenseDao.getExpenseById(expense.id)
	if (oldExpense == null) {
	    budgetDao.changeSpent(expense.budgetId, expense.cost)
	}
	else if (oldExpense.budgetId == expense.budgetId) {
	    budgetDao.changeSpent(expense.budgetId, expense.cost - oldExpense.cost)
	} else {
	    budgetDao.changeSpent(oldExpense.budgetId, -oldExpense.cost)
	    budgetDao.changeSpent(expense.budgetId, expense.cost)
	}
	expenseDao.insertOrReplaceExpenses(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
	expenseDao.deleteExpenses(expense);
    }

    suspend fun deleteBudget(budget: Budget, moveExpensesTo: Budget? = null) {
	if (moveExpensesTo != null)
	    expenseDao.moveExpensesFromOldToNewBudget(budget.id, moveExpensesTo.id)
	else
	    expenseDao.deleteExpensesByBudgetId(budget.id)

	budgetDao.deleteBudgets(budget)
    }

    suspend fun moveExpenseTo(expenseId: Long, budgetId: Long) {
	expenseDao.moveExpenseTo(expenseId, budgetId)
    }

    suspend fun moveMultipleExpensesTo(expenseIds: List<Long>, budgetId: Long) {
	for (expenseId in expenseIds) {
	    expenseDao.moveExpenseTo(expenseId, budgetId)
	}
    }
}
