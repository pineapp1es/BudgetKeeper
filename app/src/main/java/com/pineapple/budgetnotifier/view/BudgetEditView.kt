package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import java.time.ZoneId
import kotlinx.coroutines.launch
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.getSelectedEndDate
import androidx.compose.material3.getSelectedStartDate
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun BudgetEditView(budget: Budget, onSave: (Budget) -> Unit) {

    // create states for text fields
    val nameTextState = rememberTextFieldState(budget.name)
    val descTextState = rememberTextFieldState(budget.desc)
    val limitTextState = rememberTextFieldState(budget.limit.toString())
    val spentTextState = rememberTextFieldState(budget.spent.toString())
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

	    IconButton(
		modifier = Modifier,
		onClick = {
		    val editedBudget = Budget(
			id = budget.id,
			name = nameTextState.text.toString(),
			desc = descTextState.text.toString(),
			limit = limitTextState.text.toString().toDouble(),
			spent = spentTextState.text.toString().toDouble(),
			startDate = Date.from(dateRangeState.getSelectedStartDate()!!.atStartOfDay(ZoneId.systemDefault()).toInstant()),
			endDate = Date.from(dateRangeState.getSelectedEndDate()!!.atStartOfDay(ZoneId.systemDefault()).toInstant()),
		    )

		    onSave(editedBudget)
		},
	    ) {
		Icon(painterResource(R.drawable.baseline_save_24), "Save Budget")
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

		// spent text field
		TextField(
		    state = spentTextState,
		    label = { Text("Spent") },
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
