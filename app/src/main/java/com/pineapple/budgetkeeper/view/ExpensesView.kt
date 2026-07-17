package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.components.ExpenseList

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box

@Composable
fun ExpensesView(
    expenses: List<Expense>,
    onExpenseClick: (Expense) -> Unit,
    onExpenseHold: (Expense) -> Unit,
    onExpenseDelete: (Expense) -> Unit,
    onNewExpenseClick: () -> Unit,
    canCreateNew: Boolean,
) {

    Box {
	ExpenseList(
	    expenses = expenses,
	    onExpenseClick = onExpenseClick,
	    onExpenseDelete = onExpenseDelete,
	    canCreateNew = canCreateNew,
            onNewExpenseClick = onNewExpenseClick,
            onExpenseHold = onExpenseHold,
	)
    }
}
