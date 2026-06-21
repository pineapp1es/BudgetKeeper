package com.pineapple.budgetnotifier.database

import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense

import kotlinx.coroutines.flow.Flow


class BudgetRepository(
    db: BudgetNotifierDatabase,
) {

    var budgets: Flow<List<Budget>> = db.budgetDao().getAllBudgets()
    var expenses: Flow<List<Expense>> = db.expenseDao().getAllExpenses()

}
