package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.Views

import kotlin.math.min

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.pineapple.budgetnotifier.database.entities.Budget
import androidx.compose.foundation.lazy.items

@Composable
fun BudgetsView(budgets: List<Budget>, onNewClick: () -> Unit, onBudgetClick: (Long?) -> Unit) {

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
		    Card (
			modifier = Modifier,
			onClick = { onBudgetClick(budget.id) }
		    ) {
			Column {
			    Text(budget.name)
			    Text(budget.desc.substring(0, min(budget.desc.length, 10)))
			    Text(budget.limit.toString())
			    Text("-" + budget.spent.toString())
			    Text(budget.startDate.toString() + " to " + budget.endDate.toString())
			}
		    }
		}

	    }

	}
    }
}
