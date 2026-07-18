package com.pineapple.budgetkeeper.usecase

import com.pineapple.budgetkeeper.database.repository.BudgetRepository
import com.pineapple.budgetkeeper.database.repository.ExpenseRepository
import com.pineapple.budgetkeeper.database.entities.Budget

class BudgetDeleteUseCase(
    private val budgetRepo: BudgetRepository,
    private val expenseRepo: ExpenseRepository,
) {
    suspend operator fun invoke(budget: Budget, moveExpensesTo: Budget?) {
            if (moveExpensesTo != null)
                expenseRepo.moveExpensesFromOldToNewBudget(
                    budget.id,
                    moveExpensesTo.id
                )

	    budgetRepo.deleteBudget(budget)
    }
}
