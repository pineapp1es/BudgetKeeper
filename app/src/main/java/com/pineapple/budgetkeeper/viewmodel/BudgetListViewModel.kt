package com.pineapple.budgetkeeper.viewmodel

import com.pineapple.budgetkeeper.database.repository.BudgetRepository
import com.pineapple.budgetkeeper.database.repository.ExpenseRepository
import com.pineapple.budgetkeeper.uistate.BudgetListUiState
import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.usecase.BudgetDeleteUseCase
import com.pineapple.budgetkeeper.utils.status
import com.pineapple.budgetkeeper.utils.BudgetStatus

import java.util.Date

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class BudgetListViewModel(
    private val budgetRepo: BudgetRepository,
    private val expenseRepo: ExpenseRepository,
    private val budgetDeleteUseCase: BudgetDeleteUseCase,
) : ViewModel() {
    val uiState: StateFlow<BudgetListUiState> = combine(
	budgetRepo.budgets,
        expenseRepo.expenses,
    ){ budgets, expenses ->
        val active   = mutableListOf<Budget>()
        val inactive = mutableListOf<Budget>()
        val future   = mutableListOf<Budget>()
        val now = Date()

        for (budget in budgets) {
            if (budget.status(now) == BudgetStatus.ACTIVE) active.add(budget)
            else if (budget.status(now) == BudgetStatus.INACTIVE) inactive.add(budget)
            else future.add(budget)
        }

	BudgetListUiState(
	    isLoading = false,

	    activeBudgets = active,
            inactiveBudgets = inactive,
            futureBudgets = future,

            spent = expenses
                .groupBy { it.budgetId }
                .mapValues { (budgetId, expenses) ->
                    expenses.sumOf { it.cost }
                },
        )
    }.stateIn(
	scope = viewModelScope, 
	initialValue = BudgetListUiState(),
	started = SharingStarted.WhileSubscribed(5_000),
    )

    fun addOrUpdateBudget(budget: Budget) {
	viewModelScope.launch {
	    budgetRepo.addOrUpdateBudget(budget)
	}
    }

    fun deleteBudget(budget: Budget, moveExpensesTo: Budget?) {
	viewModelScope.launch {
            budgetDeleteUseCase(budget, moveExpensesTo)
	}
    }
}
