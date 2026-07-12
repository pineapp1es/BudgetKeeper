package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.components.Toast

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
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
import java.util.Calendar
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseEditView(
    expense: Expense,
    budgets: List<Budget>,
    onSave: (Expense) -> Unit,
    onDelete: (Expense) -> Unit,
    isNew: Boolean = false,
) {

    var selectedBudget: Budget by remember {
	mutableStateOf(
	    budgets.find { it.id == expense.budgetId }
		?: budgets.get(0)
	)
    }
    var budgetIdMenuExpanded by remember { mutableStateOf(false) }

    // create states for text fields
    val nameTextState = rememberTextFieldState(expense.name)
    val reasonTextState = rememberTextFieldState(expense.reason)
    val costTextState = rememberTextFieldState(expense.cost.toString())
    val date: LocalDate = expense.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val dateState = rememberDatePickerState(date)
    val timeH: Int = expense.date.get(Calendar.HOUR_OF_DAY)
    val timeM: Int = expense.date.get(Calendar.MINUTE)
    val timeState = rememberTimePickerState(timeH, timeM)

    Box(
	modifier = Modifier
	    .padding(10.dp)
    ){
	Column (
	    modifier = Modifier
		.padding(20.dp),
	){

	    Row(
		modifier = Modifier
		    .fillMaxWidth()
		    .padding(20.dp)
	       ,
	    ) {
		IconButton(
		    modifier = Modifier,
		    onClick = {


			val newDate = Calendar.getInstance()
			newDate.setTime(Date.from(dateState.getSelectedDate()!!.atStartOfDay(ZoneId.systemDefault()).toInstant()))
			newDate.set(Calendar.HOUR_OF_DAY, timeState.hour)
			newDate.set(Calendar.MINUTE, timeState.minute)
			val editedExpense = Expense(
			    id = expense.id,
			    budgetId = selectedBudget.id,
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

		if (!isNew) {
		    IconButton(
			onClick = { onDelete(expense) },
		    ) {
			Icon(painterResource(R.drawable.baseline_delete_24), "Delete Expense")
		    }
		}
	    }

	    Column (
		modifier = Modifier
		    .verticalScroll(rememberScrollState()),
	    ){

		Row {
		    Text(
			selectedBudget.name,
		    )
		    IconButton(
			onClick = { budgetIdMenuExpanded = true },
		    ) {
			Icon(painterResource(R.drawable.baseline_arrow_drop_down_24), "Expand Budget Menu")
		    }

		}

		DropdownMenu(
		    expanded = budgetIdMenuExpanded,
		    onDismissRequest = { budgetIdMenuExpanded = false },
		) {

		    budgets.forEach { budget ->
			DropdownMenuItem(
			    text = { Text(budget.name) },
			    onClick = { selectedBudget = budget; budgetIdMenuExpanded = false },
			)
		    }
		}

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
