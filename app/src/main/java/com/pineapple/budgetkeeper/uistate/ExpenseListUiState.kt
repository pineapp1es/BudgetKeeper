package com.pineapple.budgetkeeper.uistate

import com.pineapple.budgetkeeper.database.entities.Expense

data class ExpenseListUiState (
    val isLoading: Boolean = true,
    val expenses: List<Expense> = listOf(),
    val budgetNames: Map<Long, String> = mapOf(),
    val canCreateNewExpense: Boolean = false,
)
