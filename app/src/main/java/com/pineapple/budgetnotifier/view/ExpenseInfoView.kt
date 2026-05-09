package com.pineapple.budgetnotifier.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.data.Expense
import com.pineapple.budgetnotifier.data.Views
import com.pineapple.budgetnotifier.data.selected

@Composable
fun ExpenseInfoView(view: MutableState<Views>, expense: Expense /* = Expense.createNewTemplate() */) {

    val name = selected.expense?.itemName ?: "New Expense"
    val budget = (selected.budget?.name ?: "")
    val time = (selected.expense?.time ?: "").toString()
    val date = (selected.expense?.date ?: "").toString()
    val desc = (selected.expense?.desc ?: "")

    Column() {

        Row() {
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.baseline_save_24), "Save Expense Data")
            }
        }

        OutlinedTextField(
            state = rememberTextFieldState(name),
            label = { Text("Expense Name") }
        )

        // TODO
        // make this a dropdown to choose which budget
        OutlinedTextField(
            state = rememberTextFieldState(budget),
            label = { Text("Budget") }
        )

        OutlinedTextField(
            state = rememberTextFieldState(desc),
            label = { Text("Description") }
        )

        OutlinedTextField(
            state = rememberTextFieldState(time),
            label = { Text("Time of transaction") }
        )

        OutlinedTextField(
            state = rememberTextFieldState(date),
            label = { Text("Date of transaction") }
        )

    }
}