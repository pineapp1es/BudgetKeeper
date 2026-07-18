package com.pineapple.budgetkeeper.uistate

import com.pineapple.budgetkeeper.database.entities.Budget

data class BudgetListUiState (
    val activeBudgets: List<Budget> = listOf(),
    val inactiveBudgets: List<Budget> = listOf(),
    val futureBudgets: List<Budget> = listOf(),
    val selectedBudgets: List<Budget> = listOf(),
    val spent: Map<Long, Double> = mapOf(),
    val isLoading: Boolean = true,
)
