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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.util.Date
import java.time.LocalDate
import java.time.ZoneId
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.getSelectedDate
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseEditView(expense: Expense, onSave: (Expense) -> Unit) {

    // create states for text fields
    val budgetIdTextState = rememberTextFieldState(expense.budgetId.toString())
    val nameTextState = rememberTextFieldState(expense.name)
    val reasonTextState = rememberTextFieldState(expense.reason)
    val costTextState = rememberTextFieldState(expense.cost.toString())
    val date: LocalDate = expense.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val dateState = rememberDatePickerState(date)
    val timeH: Int = expense.date.getHours()
    val timeM: Int = expense.date.getMinutes()
    val timeState = rememberTimePickerState(timeH, timeM)

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
		    val newDate = Date.from(dateState.getSelectedDate()!!.atStartOfDay(ZoneId.systemDefault()).toInstant())
		    newDate.setHours(timeState.hour)
		    newDate.setMinutes(timeState.minute)
		    val editedExpense = Expense(
			id = expense.id,
			budgetId = budgetIdTextState.text.toString().toLong(),
			name = nameTextState.text.toString(),
			reason = reasonTextState.text.toString(),
			cost = costTextState.text.toString().toDouble(),
			date = newDate,
		    )

		    onSave(editedExpense)
		},
	    ) {
		Icon(painterResource(R.drawable.baseline_save_24), "Save Expense")
	    }

	    Column (
		modifier = Modifier
		    .verticalScroll(rememberScrollState()),
	    ){

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

		DatePicker(
		    modifier = Modifier
			// .verticalScroll(rememberScrollState())
			  ,
		    state = dateState,
		)

		TimePicker(
		    modifier = Modifier,
		    state = timeState,
		)

	    }
	}
    }
}
