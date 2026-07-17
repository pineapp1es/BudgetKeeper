package com.pineapple.budgetkeeper.components

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.components.Toast
import com.pineapple.budgetkeeper.components.ExpenseCard

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
    inBudget: Budget? = null,
    onNewExpenseClick: () -> Unit,
    onExpenseClick: (Expense) -> Unit,
    onExpenseHold: (Expense) -> Unit,
    onExpenseDelete: (Expense) -> Unit,
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
		    onNewExpenseClick()
		else
		    showToast = true
	    }
	) {
	    Icon(painterResource(R.drawable.baseline_add_24), "New budget")
	}

	LazyColumn {
	    items(expenses) { expense ->
                ExpenseCard(
                    expense = expense,
                    onClick = onExpenseClick,
                    onDelete = onExpenseDelete,
                    onHold = onExpenseHold,
                )
	    }
	}
    }
}
