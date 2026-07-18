package com.pineapple.budgetkeeper.viewmodel

import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.uistate.ExpenseListUiState
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

class ExpenseListViewModel(
    private val budgetRepo: BudgetRepository,
    private val expenseRepo: ExpenseRepository,
): ViewModel() {
    val uiState: StateFlow<ExpenseListUiState> = combine(
        budgetRepo.budgets,
        expenseRepo.expenses,
    ) { budgets, expenses ->
        ExpenseListUiState(
            isLoading = false,
            expenses = expenses,
            budgetNames = budgets.associate { it.id to it.name },
            canCreateNewExpense = budgets.isNotEmpty(),
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = ExpenseListUiState(),
        started = SharingStarted.WhileSubscribed(5000),
    )
}
