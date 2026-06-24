package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.database.entities.Expense

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


@Composable
fun ExpensesView(expenses: List<Expense>, onExpenseClick: (Long) -> Unit) {
    Box {
	LazyColumn {
	    items(expenses) { expense ->
		Card(
		    onClick = { onExpenseClick(expense.id) },
		) {
		    Text(expense.name)
		}
	    }
	}
    }
}
