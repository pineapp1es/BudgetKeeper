package com.pineapple.budgetkeeper.uistate

import com.pineapple.budgetkeeper.database.entities.Expense

data class ExpenseEditUiState (
    val isLoading: Boolean = true,
    val expense: Expense? = null,
    val activeBudgetNames: Map<Long, String> = mapOf(),
    val isNewExpense: Boolean = false,
)
