package com.pineapple.budgetkeeper.container

import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.database.repository.BudgetRepository
import com.pineapple.budgetkeeper.database.repository.ExpenseRepository
import com.pineapple.budgetkeeper.usecase.BudgetDeleteUseCase

class BudgetKeeperContainer(
    db: BudgetNotifierDatabase
) {
    // REPOSITORIES
    val budgetRepo by lazy {
        BudgetRepository(db)
    }
    val expenseRepo by lazy {
        ExpenseRepository(db)
    }

    // USE CASES
    val budgetDeleteUseCase = BudgetDeleteUseCase(budgetRepo, expenseRepo)
}
