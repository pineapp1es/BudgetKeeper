package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.database.entities.Expense

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier


@Composable
fun ExpensesView(expenses: List<Expense>, onExpenseClick: (Long?) -> Unit) {
    Box {
	Column {

	    IconButton(
		modifier = Modifier,
		onClick = { onExpenseClick(null) }
	    ) {
		Icon(painterResource(R.drawable.baseline_add_24), "New budget")
	    }

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
}
