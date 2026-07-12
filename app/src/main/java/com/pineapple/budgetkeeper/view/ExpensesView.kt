package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.components.ExpenseList

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box

@Composable
fun ExpensesView(
    expenses: List<Expense>,
    onExpenseClick: (Long?, Long?) -> Unit,
    onDelete: (Expense) -> Unit,
    canCreateNew: Boolean,
) {

    Box {
	ExpenseList(
	    expenses,
	    onExpenseClick,
	    onDelete,
	    canCreateNew,
	)
    }
}
