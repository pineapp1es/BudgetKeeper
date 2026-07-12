package com.pineapple.budgetkeeper.components

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.components.Toast

import java.util.Calendar

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
fun ExpenseList(
    expenses: List<Expense>,
    inBudget: Long? = null,
    onExpenseClick: (Long?, Long?) -> Unit,
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

    Column {
	IconButton(
	    modifier = Modifier,
	    onClick = {
		if (canCreateNew)
		    onExpenseClick(null, inBudget)
		else
		    showToast = true
	    }
	) {
	    Icon(painterResource(R.drawable.baseline_add_24), "New budget")
	}

	LazyColumn {
	    items(expenses) { expense ->

		var dateString = "" +
		    expense.date.get(Calendar.DATE).toString().padStart(2, '0') + "-" +
		    (expense.date.get(Calendar.MONTH)+1).toString().padStart(2, '0') + "-" +
		    expense.date.get(Calendar.YEAR)

		Card(
		    onClick = { onExpenseClick(expense.id, expense.budgetId) },
		) {
		    Row {
			IconButton(
			    onClick = { onDelete(expense) },
			) {
			    Icon(painterResource(R.drawable.baseline_delete_24), "Delete Expense")
			}

			Column {
			    Text(expense.name)
			    Text(expense.cost.toString())
			    Text(dateString)
			}
		    }
		}
	    }
	}
    }
}
