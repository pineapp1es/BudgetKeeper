package com.pineapple.budgetnotifier.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.data.Views
import com.pineapple.budgetnotifier.data.activeBudgets


@Composable
fun ExpensesView(view: MutableState<Views>) {
    val update = remember { mutableStateOf(false) }
    Column() {

        key(update) {
            LazyColumn(
                modifier = Modifier
                    .height(500.dp)
            ) {
                activeBudgets.forEach { budget ->
                    stickyHeader {
                        Text(budget.name)
                    }
                    items(budget.expenses) { expense ->
                        Card {
                            Row {
                                Column {
                                    Text(expense.itemName)
                                    Text(expense.spentAmount.toString())
                                    Text(expense.time.toString() + " " + expense.date.toString())
                                }
                                IconButton(onClick = {
                                    budget.removeExpense(expense)
                                    update.value = !update.value
                                }) {
                                    Icon(painterResource(R.drawable.baseline_delete_24), "Delete Expense")
                                }
                                IconButton(onClick = {}) {
                                    Icon(painterResource(R.drawable.baseline_more_vert_24), "About Expense")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
