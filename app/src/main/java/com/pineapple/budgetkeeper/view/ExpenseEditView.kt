package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.components.Toast
import com.pineapple.budgetkeeper.uistate.ExpenseEditUiState

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
import androidx.compose.material3.HorizontalDivider
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
    uiState: ExpenseEditUiState,
    onSave: (Expense) -> Unit,
    onDelete: (Expense) -> Unit,
) {

    val expense = uiState.expense!!
    val budgets = uiState.activeBudgetNames
    val isNew = uiState.isNewExpense

    var toastMessage by remember { mutableStateOf("") }
    if (toastMessage != "") {
	Toast(
	    title = { Text(toastMessage) },
	    content = {},
	    onDismiss = { toastMessage = "" },
	    xoffset = 20.dp,
	    yoffset = 20.dp,
	)
    }

    var selectedBudget by remember { mutableStateOf(expense.budgetId) }
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

			var validInputs = true
			var newCost: Double = 0.0

			try {
			    newCost = costTextState.text.toString().toDouble()
			    if (newCost <= 0) throw Exception()
			}
			catch(e: Exception) {
			    toastMessage = "Enter a valid cost"
			    validInputs = false
			}

			val newDate = Calendar.getInstance()
			newDate.setTime(Date.from(dateState.getSelectedDate()!!.atStartOfDay(ZoneId.systemDefault()).toInstant()))
			newDate.set(Calendar.HOUR_OF_DAY, timeState.hour)
			newDate.set(Calendar.MINUTE, timeState.minute)

			if (validInputs) {
			    val editedExpense = Expense(
				id = expense.id,
				budgetId = selectedBudget,
				name = nameTextState.text.toString(),
				reason = reasonTextState.text.toString(),
				cost = newCost,
				date = newDate,
			    )

			    onSave(editedExpense)
			}
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
			budgets[selectedBudget] ?: "",
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
			    text = { Text(budget.value) },
			    onClick = {
                                selectedBudget = budget.key
                                budgetIdMenuExpanded = false
                            },
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
