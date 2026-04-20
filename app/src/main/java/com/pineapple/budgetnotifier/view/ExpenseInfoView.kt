package com.pineapple.budgetnotifier.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.data.selected

@Composable
fun ExpenseInfoView(view: MutableState<String>) {
    val title = selected.expense?.itemName ?: "New Expense"
    Column() {

        Row() {
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.baseline_save_24), "Save Expense Data")
            }
        }

        OutlinedTextField(
            state = rememberTextFieldState(title),
            label = { Text("Expense Name") }
        )

        OutlinedTextField(
            state = rememberTextFieldState((selected.budget?.name ?: "")),
            label = { Text("Budget") }
        )

        OutlinedTextField(
            state = rememberTextFieldState((selected.expense?.time ?: "").toString()),
            label = { Text("Time") }
        )

        OutlinedTextField(
            state = rememberTextFieldState((selected.expense?.date ?: "").toString()),
            label = { Text("Date") }
        )

        OutlinedTextField(
            state = rememberTextFieldState((selected.expense?.desc ?: "")),
            label = { Text("Description") }
        )
    }
}