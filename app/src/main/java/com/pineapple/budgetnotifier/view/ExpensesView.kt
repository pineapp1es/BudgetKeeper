package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.database.entities.Expense
import com.pineapple.budgetnotifier.components.Toast

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier


@Composable
fun ExpensesView(
    expenses: List<Expense>,
    onExpenseClick: (Long?) -> Unit,
    onDelete: (Expense) -> Unit,
    canCreateNew: Boolean,
) {
    var showToast by remember { mutableStateOf(false) }

    if (showToast) {
	Toast(
	    title = {},
	    content = { Text("No budgets") },
	    onDismiss = { showToast = false },
	    xoffset = 20.dp,
	    yoffset = 20.dp,
	)
    }

    Box {
	Column {

	    IconButton(
		modifier = Modifier,
		onClick = {
		    if (canCreateNew)
			onExpenseClick(null)
		    else
			showToast = true
		}
	    ) {
		Icon(painterResource(R.drawable.baseline_add_24), "New budget")
	    }

	    LazyColumn {
		items(expenses) { expense ->
		    Card(
			onClick = { onExpenseClick(expense.id) },
		    ) {
			Row {
			    Text(expense.name)
			    IconButton(
				onClick = { onDelete(expense) },
			    ) {
				Icon(painterResource(R.drawable.baseline_delete_24), "Delete Expense")
			    }
			}
		    }
		}
	    }
	}
    }
}
