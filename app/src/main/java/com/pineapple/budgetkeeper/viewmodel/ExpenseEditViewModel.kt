package com.pineapple.budgetkeeper.viewmodel

import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.uistate.ExpenseEditUiState
import com.pineapple.budgetkeeper.database.repository.BudgetRepository
import com.pineapple.budgetkeeper.database.repository.ExpenseRepository
import com.pineapple.budgetkeeper.usecase.BudgetDeleteUseCase
import com.pineapple.budgetkeeper.utils.status
import com.pineapple.budgetkeeper.utils.BudgetStatus

import java.util.Date

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseEditViewModel(
    private val expenseId: Long?,
    private val budgetRepo: BudgetRepository,
    private val expenseRepo: ExpenseRepository,
): ViewModel() {
    val uiState: StateFlow<ExpenseEditUiState> = combine(
        budgetRepo.budgets,
        expenseRepo.expenses,
    ) { budgets, expenses ->
        val now = Date()
        val activeBudgets = budgets.filter { it.status(now) == BudgetStatus.ACTIVE }
        ExpenseEditUiState(
            isLoading = false,
            expense = expenses.find { it.id == expenseId },
            activeBudgetNames = activeBudgets.associate { it.id to it.name },
            isNewExpense = expenseId == 0L,
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = ExpenseEditUiState(),
        started = SharingStarted.WhileSubscribed(5000),
    )
}
