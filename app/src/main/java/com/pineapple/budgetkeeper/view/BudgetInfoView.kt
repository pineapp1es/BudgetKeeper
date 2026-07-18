package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.components.ExpenseList
import com.pineapple.budgetkeeper.uistate.BudgetInfoUiState

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
fun BudgetInfoView(
    uiState: BudgetInfoUiState,
    onBudgetEdit: (Budget) -> Unit,
    onBudgetDelete: (Budget) -> Unit,
    onNewExpenseClick: () -> Unit,
    onExpenseClick: (Expense) -> Unit,
    onExpenseHold: (Expense) -> Unit,
    onExpenseDelete: (Expense) -> Unit,
) {

    Box {
	Column {
	    BudgetSection(
                budget = uiState.budget!!,
                onBudgetEdit = onBudgetEdit,
                onBudgetDelete = onBudgetDelete,
            )
	    ExpensesSection(
                expenses = uiState.expenses,
                budget = uiState.budget,
                onExpenseClick = onExpenseClick,
                onExpenseDelete = onExpenseDelete,
                onExpenseHold = onExpenseHold,
                onNewExpenseClick = onNewExpenseClick,
            )
	}
    }

}

@Composable
fun BudgetSection(
    budget: Budget,
    onBudgetEdit: (Budget) -> Unit,
    onBudgetDelete: (Budget) -> Unit,
) {

    Column(modifier = Modifier.offset(x = 80.dp, y = 20.dp)) {
	Row(
	    modifier = Modifier
		.fillMaxWidth()
		.padding(20.dp)
	   ,
	) {
	    Text(budget.name)
	    IconButton(
		modifier = Modifier,
		onClick = { onBudgetEdit(budget) }
	    ) {
		Icon(painterResource(R.drawable.baseline_edit_24), "Edit Budget")
	    }

	    IconButton(
		modifier = Modifier,
		onClick = { onBudgetDelete(budget) }
	    ) {
		Icon(painterResource(R.drawable.baseline_delete_24), "Delete Budget")
	    }
	}
	Text(budget.desc)

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
fun ExpensesSection(
    expenses: List<Expense>,
    budget: Budget,
    onExpenseHold: (Expense) -> Unit,
    onExpenseClick: (Expense) -> Unit,
    onExpenseDelete: (Expense) -> Unit,
    onNewExpenseClick: () -> Unit,
) {
    ExpenseList(
	expenses = expenses,
	inBudget = budget,
        onNewExpenseClick = onNewExpenseClick,
        onExpenseHold = onExpenseHold,
	onExpenseClick = onExpenseClick,
	onExpenseDelete = onExpenseDelete,
	canCreateNew = true,
    )
}
