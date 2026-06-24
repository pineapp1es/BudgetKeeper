package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.database.entities.Expense
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.util.Date
import androidx.compose.foundation.text.input.rememberTextFieldState

@Composable
fun ExpenseInfoView(expense: Expense, onSave: (Expense) -> Unit) {

    // create states for text fields
    val budgetIdTextState = rememberTextFieldState(expense.budgetId.toString())
    val nameTextState = rememberTextFieldState(expense.name)
    val reasonTextState = rememberTextFieldState(expense.reason)
    val costTextState = rememberTextFieldState(expense.cost.toString())


    Box(
	modifier = Modifier
	    .padding(10.dp)
    ){
	Column (
	    modifier = Modifier
		.padding(20.dp),
	){

	    IconButton(
		modifier = Modifier,
		onClick = {
		    val editedExpense = Expense(
			id = expense.id,
			budgetId = budgetIdTextState.text.toString().toLong(),
			name = nameTextState.text.toString(),
			reason = reasonTextState.text.toString(),
			cost = costTextState.text.toString().toDouble(),
			date = Date(),
		    )

		    onSave(editedExpense)
		},
	    ) {
		Icon(painterResource(R.drawable.baseline_save_24), "Save Expense")
	    }

	    Column {

		// budgetid text field
		TextField(
		    state = budgetIdTextState,
		    label = { Text("budgetId") },
		)

		// name text field
		TextField(
		    state = nameTextState,
		    label = { Text("Name") },
		)

		// reason text field
		TextField(
		    state = reasonTextState,
		    label = { Text("Reason") },
		)

		// cost text field
		TextField(
		    state = costTextState,
		    label = { Text("Cost") },
		)

	    }
	}
    }
}
