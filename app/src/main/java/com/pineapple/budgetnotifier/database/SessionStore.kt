package com.pineapple.budgetnotifier.database

import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense

object SessionStore {

    var budgetRecords: Array<Budget>? = null;
    var expenseRecords: Array<Expense>? = null;

    fun loadAllBudgetRecords(db: BudgetNotifierDatabase) {
        val budgetDao = db.budgetDao()
        this.budgetRecords = budgetDao.loadAllBudgets()
    }

    fun loadAllExpenseRecords(db: BudgetNotifierDatabase) {
        val budgetDao = db.budgetDao()
        this.budgetRecords = budgetDao.loadAllBudgets()
    }

}
