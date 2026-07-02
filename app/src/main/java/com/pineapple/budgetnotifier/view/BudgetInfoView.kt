package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource

@Composable
fun BudgetInfoView(budget: Budget,
		   expenses: List<Expense>,
		   onBudgetEdit: (Long?) -> Unit,
		   onBudgetDelete: (Budget, Long?) -> Unit,
		   onExpenseClick: (Long?, Long?) -> Unit,
) {

    Box {
	Column {
	    BudgetSection(budget, onBudgetEdit, onBudgetDelete)
	    ExpensesSection(expenses, budget.id, onExpenseClick)
	}
    }

}

@Composable
fun BudgetSection(budget: Budget, onBudgetEdit: (Long?) -> Unit, onBudgetDelete: (Budget, Long?) -> Unit) {

    Column(modifier = Modifier.offset(x = 80.dp, y = 20.dp)) {
	Row(
	    modifier = Modifier
		.fillMaxWidth()
		.padding(20.dp)
	   ,
	) {
	    IconButton(
		modifier = Modifier,
		onClick = { onBudgetEdit(budget.id) }
	    ) {
		Icon(painterResource(R.drawable.baseline_edit_24), "Edit Budget")
	    }

	    IconButton(
		modifier = Modifier,
		onClick = { onBudgetDelete(budget, null) }
	    ) {
		Icon(painterResource(R.drawable.baseline_delete_24), "Delete Budget")
	    }
	}

	Row(
            modifier = Modifier
		.fillMaxWidth()
		.padding(20.dp)
	) {
            val budgetLeft = budget.limit - budget.spent
            if (budgetLeft > 0) Text("+", modifier = Modifier.padding(20.dp))
            else Text("-", modifier = Modifier.padding(20.dp))
            Text(
		budgetLeft.toString(),
		modifier = Modifier.padding(20.dp)
            )
            Column() {
		Text(budget.limit.toString())
		Text("-" + budget.spent.toString())
            }
	}
    }
}


@Composable
fun ExpensesSection(expenses: List<Expense>, budgetId: Long, onExpenseClick: (Long?, Long?) -> Unit) {
    Column {
	IconButton(
	    modifier = Modifier,
	    onClick = { onExpenseClick(null, budgetId) }
	) {
	    Icon(painterResource(R.drawable.baseline_add_24), "New Expense")
	}
	
	LazyColumn {
	    items(expenses) { expense ->
		Card(
		    modifier = Modifier,
		    onClick = { onExpenseClick(expense.id, budgetId) }
		) {
		    Text(expense.name)
		}
	    }
	}
    }
}
