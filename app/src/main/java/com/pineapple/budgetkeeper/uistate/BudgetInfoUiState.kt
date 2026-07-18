package com.pineapple.budgetkeeper.uistate

import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.database.entities.Expense

data class BudgetInfoUiState(
    val isLoading: Boolean = true,
    val showMoveDialog: Boolean = false,
    val budget: Budget? = null,
    val spent: Double = 0.0,
    val expenses: List<Expense> = listOf(),
    val selectedExpenses: List<Expense> = listOf(),
)
