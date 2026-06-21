package com.pineapple.budgetnotifier.view

import androidx.compose.foundation.text.input.rememberTextFieldState
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
import kotlinx.coroutines.launch

@Composable
fun BudgetInfoView() {

//     // create states for text fields
//     val idTextState = rememberTextFieldState(budget.id)
//     val nameTextState = rememberTextFieldState(budget.name)
//     val descTextState = rememberTextFieldState(budget.desc)
//     val limitTextState = rememberTextFieldState(budget.limit.toString())
//     val spentTextState = rememberTextFieldState(budget.spent.toString())


//     Box(
// 	modifier = Modifier
// 	    .padding(10.dp)
//     ){
// 	Column (
// 	    modifier = Modifier
// 		.padding(20.dp),
// 	){

// 	    IconButton(
// 		modifier = Modifier,
// 		onClick = {
// 		    val editedBudget = Budget(
// 			id = idTextState.text.toString(),
// 			name = nameTextState.text.toString(),
// 			desc = descTextState.text.toString(),
// 			limit = limitTextState.text.toString().toDouble(),
// 			spent = spentTextState.text.toString().toDouble(),
// 			startDate = Date(),
// 			endDate = Date(),
// 		    )
// 		},
// 	    ) {
// 		Icon(painterResource(R.drawable.baseline_save_24), "Save Expense")
// 	    }

// 	    Column {
// 		// id text field
// 		TextField(
// 		    state = idTextState,
// 		    label = { Text("ID") },
// 		)

// 		// name text field
// 		TextField(
// 		    state = nameTextState,
// 		    label = { Text("Name") },
// 		)

// 		// desc text field
// 		TextField(
// 		    state = descTextState,
// 		    label = { Text("Description") },
// 		)

// 		// limit text field
// 		TextField(
// 		    state = limitTextState,
// 		    label = { Text("Limit") },
// 		)

// 		// spent text field
// 		TextField(
// 		    state = spentTextState,
// 		    label = { Text("Spent") },
// 		)

// 	    }
// 	}
//     }
}
