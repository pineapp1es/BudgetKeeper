package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.uistate.ExpenseListUiState
import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.components.ExpenseList

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box

@Composable
fun ExpensesView(
    uiState: ExpenseListUiState,
    onExpenseClick: (Expense) -> Unit,
    onExpenseHold: (Expense) -> Unit,
    onExpenseDelete: (Expense) -> Unit,
    onNewExpenseClick: () -> Unit,
) {

    Box {
	ExpenseList(
	    expenses = uiState.expenses,
	    canCreateNew = uiState.canCreateNewExpense,
            budgetNames = uiState.budgetNames,
	    onExpenseClick = onExpenseClick,
	    onExpenseDelete = onExpenseDelete,
            onNewExpenseClick = onNewExpenseClick,
            onExpenseHold = onExpenseHold,
	)
    }
}
