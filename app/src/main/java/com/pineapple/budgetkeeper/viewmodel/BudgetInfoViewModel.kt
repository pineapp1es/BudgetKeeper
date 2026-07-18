package com.pineapple.budgetkeeper.viewmodel

import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.uistate.BudgetInfoUiState
import com.pineapple.budgetkeeper.database.repository.BudgetRepository
import com.pineapple.budgetkeeper.database.repository.ExpenseRepository
import com.pineapple.budgetkeeper.usecase.BudgetDeleteUseCase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BudgetInfoViewModel(
    private val budgetId: Long,
    private val budgetRepo: BudgetRepository,
    private val expenseRepo: ExpenseRepository,
    private val budgetDeleteUseCase: BudgetDeleteUseCase,
): ViewModel() {
    val uiState: StateFlow<BudgetInfoUiState> = combine(
        budgetRepo.budgets,
        expenseRepo.expenses,
    ) { budgets, expenses ->
        val budgetExpenses = expenses.filter { it.budgetId == budgetId }
        BudgetInfoUiState(
            isLoading = false,
            budget = budgets.find { it.id == budgetId },
            spent = budgetExpenses.sumOf { it.cost },
            expenses = budgetExpenses,
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = BudgetInfoUiState(),
        started = SharingStarted.WhileSubscribed(5000),
    )
}
