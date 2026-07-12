package com.pineapple.budgetkeeper.components

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.entities.Budget

import kotlin.math.min
import java.util.Calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable

@Composable
fun BudgetCard(
    budget: Budget,
    onBudgetClick: (Long?) -> Unit,
    onDelete: (Budget, Long?) -> Unit,
) {
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
