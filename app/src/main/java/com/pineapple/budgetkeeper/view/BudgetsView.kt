package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.Views
import com.pineapple.budgetkeeper.components.BudgetCard

import java.util.Date

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

    val activeBudgets = budgets.filter {
	it.endDate.getTime() > Date() &&
	it.startDate.getTime() < Date()
    }
    val oldBudgets = budgets.filter { it.endDate.getTime() < Date() }
    val futureBudgets = budgets.filter { it.startDate.getTime() > Date() }

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

		stickyHeader {
		    Text("Active")
		}
		items(activeBudgets) { budget ->
		    BudgetCard(
			budget = budget,
			onBudgetClick = onBudgetClick,
			onDelete = onDelete,
		    )
		}

		stickyHeader {
		    Text("Future")
		}
		items(futureBudgets) { budget ->
		    BudgetCard(
			budget = budget,
			onBudgetClick = onBudgetClick,
			onDelete = onDelete,
		    )
		}

		stickyHeader {
		    Text("Inactive")
		}
		items(oldBudgets) { budget ->
		    BudgetCard(
			budget = budget,
			onBudgetClick = onBudgetClick,
			onDelete = onDelete,
		    )
		}


	    }

	}
    }
}

