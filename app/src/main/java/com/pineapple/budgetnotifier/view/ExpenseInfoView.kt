package com.pineapple.budgetnotifier.view

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.pineapple.budgetnotifier.Views
import com.pineapple.budgetnotifier.database.Selected
import com.pineapple.budgetnotifier.database.entities.Expense
import com.pineapple.budgetnotifier.utils.getRandomString
import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.exp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpenseInfoView(view: MutableState<Views>, expense: Expense = Expense.newExpense(Selected.budget.id)) {

    val id = Selected.expense?.id ?: getRandomString(10)
    val idState = rememberTextFieldState(id)

    val budgetId = (Selected.budget?.budgetId ?: "")
    val budgetState = rememberTextFieldState(budgetId)

    val name = Selected.expense?.name ?: "New Expense"
    val nameState = rememberTextFieldState(name)

    val time = (Selected.expense?.time ?: "").toString()
    val timeState = rememberTextFieldState(time)

    val date = (Selected.expense?.date ?: "").toString()
    val dateState = rememberTextFieldState(date)

    val desc = (Selected.expense?.desc ?: "")
    val descState = rememberTextFieldState(desc)

    Column() {

        Row() {
            IconButton(onClick = {
                expense = Expense(
                    expense.budgetIds,
                    nameState.toString(),
                    expense.spentAmount,
                    LocalTime(timeState.text.toString()),
                    LocalDate(dateState.text.toString()),
                    descState.text.toString()
                )
            }) {
                Icon(painterResource(R.drawable.baseline_save_24), "Save Expense Data")
            }
        }

        OutlinedTextField(
            state = nameState,
            label = { Text("Expense Name") }
        )

        // TODO
        // make this a dropdown to choose which budget
        OutlinedTextField(
            state = budgetState,
            label = { Text("Budget") }
        )

        OutlinedTextField(
            state = descState,
            label = { Text("Description") }
        )

        OutlinedTextField(
            state = timeState,
            label = { Text("Time of transaction") },
        )

        OutlinedTextField(
            state = dateState,
            label = { Text("Date of transaction") }
        )

    }
}
