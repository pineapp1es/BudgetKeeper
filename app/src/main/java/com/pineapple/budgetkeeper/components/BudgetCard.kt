package com.pineapple.budgetkeeper.components

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.database.entities.Budget

import kotlin.math.min
import java.util.Calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp

@Composable
fun BudgetCard(
    budget: Budget,
    onClick: (Budget) -> Unit,
    onDelete: (Budget, Budget?) -> Unit,
    onHold: (Budget) -> Unit = { budget -> },
) {

    var showToast by remember { mutableStateOf(false) }

    if (showToast) {
	Toast(
	    title = { Text("Held") },
	    content = {},
	    onDismiss = { showToast = false },
	    xoffset = 20.dp,
	    yoffset = 20.dp,
	)
    }

    val defaultCardColors = CardDefaults.cardColors()
    val heldCardColors = CardColors(
        containerColor = Color.Blue,
        contentColor = Color.Black,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.Black,
    )
    var cardColors by remember { mutableStateOf(defaultCardColors) }



    var startDateString = "" +
	budget.startDate.get(Calendar.DATE).toString().padStart(2, '0') + "-" +
	(budget.startDate.get(Calendar.MONTH)+1).toString().padStart(2, '0') + "-" +
	budget.startDate.get(Calendar.YEAR)
    var endDateString = "" +
	budget.endDate.get(Calendar.DATE).toString().padStart(2, '0') + "-" +
	(budget.endDate.get(Calendar.MONTH)+1).toString().padStart(2, '0') + "-" +
	budget.endDate.get(Calendar.YEAR)

    Card (
	modifier = Modifier
            .combinedClickable(
                onClick = {
                    onClick(budget)
                },
                onLongClick = {
                    cardColors = heldCardColors
                    onHold(budget)
                },
            )
         ,
        colors = cardColors,
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
		Text(startDateString + " to " + endDateString)
                Text((budget.limit - budget.spent).toString() +
                         " (" + budget.spent.toString() + "/" + budget.limit.toString() + ")")
		Text(budget.desc.substring(0, min(budget.desc.length, 10)))
	    }

	}
    }
}
