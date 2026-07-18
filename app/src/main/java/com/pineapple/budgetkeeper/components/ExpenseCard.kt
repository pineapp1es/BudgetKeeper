package com.pineapple.budgetkeeper.components

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.entities.Expense

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
fun ExpenseCard(
    expense: Expense,
    budgetName: String?,
    onClick: (Expense) -> Unit,
    onHold: (Expense) -> Unit,
    onDelete: (Expense) -> Unit,
) {
    var dateString = "" +
	expense.date.get(Calendar.DATE).toString().padStart(2, '0') + "-" +
	(expense.date.get(Calendar.MONTH)+1).toString().padStart(2, '0') + "-" +
	expense.date.get(Calendar.YEAR)

    Card(
	onClick = { onClick(expense) },
    ) {
	Row {
	    IconButton(
		onClick = { onDelete(expense) },
	    ) {
		Icon(painterResource(R.drawable.baseline_delete_24), "Delete Expense")
	    }

	    Column {
		Text(expense.name)
                if (budgetName != null) Text("in" + budgetName)
		Text(expense.cost.toString())
		Text(dateString)
	    }
	}
    }

}
