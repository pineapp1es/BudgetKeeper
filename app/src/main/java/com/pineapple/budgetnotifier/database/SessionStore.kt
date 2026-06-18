package com.pineapple.budgetnotifier.database

import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense

object {

    var budgetRecords: Array<Budget>? = null;
    var expenseRecords: Array<Expense>? = null;

    fun loadAllBudgetRecords(db: BudgetNotifierDatabase) {
        
        this.budgetRecords = db.
    }
}
