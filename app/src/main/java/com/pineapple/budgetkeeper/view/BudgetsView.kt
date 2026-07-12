package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.Views
import com.pineapple.budgetkeeper.components.Toast

import kotlin.math.min
import java.util.Calendar

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import com.pineapple.budgetkeeper.database.entities.Budget
import androidx.compose.foundation.lazy.items

@Composable
fun BudgetsView(
    budgets: List<Budget>,
    onNewClick: () -> Unit,
    onBudgetClick: (Long?) -> Unit,
    onDelete: (Budget, Long?) -> Unit,
) {

    // val activeBudgets = budgets.filter { it.endDate > Date() }

    Box(
	modifier = Modifier
	    .padding(30.dp),
    ) {
	Column {
	    IconButton(
		modifier = Modifier,
		onClick = { onNewClick() }
	    ) {
		Icon(painterResource(R.drawable.baseline_add_24), "New budget")
	    }


	    LazyColumn(
		modifier = Modifier
	    ) {

		items(budgets) { budget ->

		    var startDateString = "" +
			budget.startDate.get(Calendar.DATE).toString().padStart(2, '0') + "-" +
			(budget.startDate.get(Calendar.MONTH)+1).toString().padStart(2, '0') + "-" +
			budget.startDate.get(Calendar.YEAR)
		    var endDateString = "" +
			budget.endDate.get(Calendar.DATE).toString().padStart(2, '0') + "-" +
			(budget.endDate.get(Calendar.MONTH)+1).toString().padStart(2, '0') + "-" +
			budget.endDate.get(Calendar.YEAR)

		    Card (
			modifier = Modifier,
			onClick = { onBudgetClick(budget.id) }
		    ) {
			Row {

			    IconButton(
				modifier = Modifier,
				onClick = { onDelete(budget, null) }
			    ) {
				Icon(painterResource(R.drawable.baseline_delete_24), "Delete Budget")
			    }

			    Column {
				Text(budget.name)
				Text(budget.desc.substring(0, min(budget.desc.length, 10)))
				Text(budget.limit.toString())
				Text("-" + budget.spent.toString())
				Text(startDateString + " to " + endDateString)
			    }

			}
		    }
		}

	    }

	}
    }
}
