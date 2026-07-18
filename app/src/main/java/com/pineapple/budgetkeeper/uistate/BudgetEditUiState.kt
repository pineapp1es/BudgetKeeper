package com.pineapple.budgetkeeper.uistate

import com.pineapple.budgetkeeper.database.entities.Budget

data class BudgetEditUiState (
    val isLoading: Boolean = true,
    val budget: Budget? = null,
)
