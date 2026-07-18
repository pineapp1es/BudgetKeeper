package com.pineapple.budgetkeeper.viewmodel

import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.uistate.BudgetEditUiState
import com.pineapple.budgetkeeper.database.repository.BudgetRepository
import com.pineapple.budgetkeeper.database.repository.ExpenseRepository
import com.pineapple.budgetkeeper.usecase.BudgetDeleteUseCase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BudgetEditViewModel(
    private val budgetId: Long,
    private val budgetRepo: BudgetRepository,
    private val budgetDeleteUseCase: BudgetDeleteUseCase,
): ViewModel() {
    val uiState: StateFlow<BudgetEditUiState> = budgetRepo.budgets
        .map { budgets ->
            BudgetEditUiState(
                isLoading = false,
                budget = budgets.find { it.id == budgetId },
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = BudgetEditUiState(),
            started = SharingStarted.WhileSubscribed(5000),
        )
}
