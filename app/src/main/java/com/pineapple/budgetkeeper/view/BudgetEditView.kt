package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.components.Toast
import com.pineapple.budgetkeeper.uistate.BudgetEditUiState

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.util.Date
import java.time.LocalDate
import java.util.Calendar
import java.time.ZoneId
import kotlinx.coroutines.launch
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.getSelectedEndDate
import androidx.compose.material3.getSelectedStartDate
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf


@Composable
fun BudgetEditView(
    uiState: BudgetEditUiState,
    onSave: (Budget) -> Unit,
    onDelete: (Budget) -> Unit,
    isNew: Boolean = false,
) {

    val budget = uiState.budget!!

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

    // create states for text fields
    val nameTextState = rememberTextFieldState(budget.name)
    val descTextState = rememberTextFieldState(budget.desc)
    val limitTextState = rememberTextFieldState(budget.limit.toString())
    val startDate: LocalDate = budget.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
    val endDate: LocalDate = budget.endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
    val dateRangeState = rememberDateRangePickerState(startDate, endDate);


    Box(
	modifier = Modifier
	    .padding(10.dp)
    ){
	Column (
	    modifier = Modifier
		.padding(20.dp),
	){
	    Row (
		modifier = Modifier
		    .fillMaxWidth()
		    .padding(20.dp)
		,
	    ) {

		IconButton(
		    modifier = Modifier,
		    onClick = {

			var validInputs = true
			var newLimit: Double = 0.0

			try {
			    newLimit = limitTextState.text.toString().toDouble()
			    if (newLimit <= 0) throw Exception()
			}
			catch(e: Exception) {
			    toastMessage = "Enter a valid limit"
			    validInputs = false
			}

			val startCal = Calendar.getInstance()
			startCal.setTime(Date.from(dateRangeState.getSelectedStartDate()!!.atStartOfDay(ZoneId.systemDefault()).toInstant()))

			val endCal = Calendar.getInstance()
			endCal.setTime(Date.from(dateRangeState.getSelectedEndDate()!!.atStartOfDay(ZoneId.systemDefault()).toInstant()))

			if (validInputs) {
			    val editedBudget = Budget(
				id = budget.id,
				name = nameTextState.text.toString(),
				desc = descTextState.text.toString(),
				limit = newLimit,
				spent = budget.spent,
				startDate = startCal,
				endDate = endCal,
			    )

			    onSave(editedBudget)
			}
			
		    },
		) {
		    Icon(painterResource(R.drawable.baseline_save_24), "Save Budget")
		}

		if (!isNew) {
		    IconButton(
			modifier = Modifier,
			onClick = { onDelete(budget) }
		    ) {
			Icon(painterResource(R.drawable.baseline_delete_24), "Delete Budget")
		    }
		}

	    }


	    Column {

		// name text field
		TextField(
		    state = nameTextState,
		    label = { Text("Name") },
		)

		// desc text field
		TextField(
		    state = descTextState,
		    label = { Text("Description") },
		)

		// limit text field
		TextField(
		    state = limitTextState,
		    label = { Text("Limit") },
		)

		// date range picker !!
		DateRangePicker(
		    modifier = Modifier,
		    state = dateRangeState,
		    title = { Text("Budget Date Range") },
		    showModeToggle = true,
		)

	    }
	}
    }
}
