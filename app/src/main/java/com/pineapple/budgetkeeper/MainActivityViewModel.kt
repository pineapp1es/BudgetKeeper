package com.pineapple.budgetkeeper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pineapple.budgetkeeper.database.BudgetRepository
import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.database.entities.Expense
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MainActivityViewModel(
    val budgetRepo: BudgetRepository,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = combine(
	budgetRepo.budgets,
	budgetRepo.expenses,
    ){ budgets, expenses ->
	MainActivityUiState(
	    isLoading = false,
	    budgets = budgets,
	    expenses = expenses,
	)
    }.stateIn(
	scope = viewModelScope, 
	initialValue = MainActivityUiState(),
	started = SharingStarted.WhileSubscribed(5_000),
    )

    fun correctBudgetSpent() {
	viewModelScope.launch {
	    budgetRepo.correctAllBudgets()
	}
    }

    fun addOrUpdateBudget(budget: Budget) {
	viewModelScope.launch {
	    budgetRepo.addOrUpdateBudget(budget)
	}
    }

    fun addOrUpdateExpense(expense: Expense) {
	viewModelScope.launch {
	    budgetRepo.addOrUpdateExpense(expense)
	}
    }

    fun deleteBudget(budget: Budget, moveExpensesTo: Long?) {
	viewModelScope.launch {
	    budgetRepo.deleteBudget(budget, moveExpensesTo)
	}
    }

    fun deleteExpense(expense: Expense) {
	viewModelScope.launch {
	    budgetRepo.deleteExpense(expense)
	}
    }

    fun moveExpenseTo(expenseId: Long, budgetId: Long) {
	viewModelScope.launch {
	    budgetRepo.moveExpenseTo(expenseId, budgetId)
	}
    }

}

class MainActivityViewModelFactory(
    private val repository: BudgetRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}

data class MainActivityUiState (
    val isLoading: Boolean = true,
    val budgets: List<Budget> = listOf() ,
    val expenses: List<Expense> = listOf(),
) {
    fun getBudgetByIdOrNew(budgetId: Long?): Budget {
	return budgets.find { it.id == budgetId }
	    ?: Budget.newBudget()
    }

    fun getExpenseByIdOrNew(expenseId: Long?, budgetId: Long? ): Expense {
	return expenses.find { it.id == expenseId }
	    ?: Expense.newExpense(budgetId ?: mostRecentlyUsedBudgetId())
    }

    fun getExpenseByIdOrNull(expenseId: Long?): Expense? {
	return expenses.find { it.id == expenseId }
    }

    fun mostRecentlyUsedBudgetId(): Long {
	val mostRecentExpense: Expense? = expenses.maxByOrNull { it.date }

	return mostRecentExpense?.budgetId ?: 0
    }

    fun getExpensesByBudgetId(budgetId: Long): List<Expense> {
	return expenses.filter { it.budgetId == budgetId }
    }
}
