package com.pineapple.budgetnotifier.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.data.Budget
import com.pineapple.budgetnotifier.data.Views
import com.pineapple.budgetnotifier.data.activeBudgets
import com.pineapple.budgetnotifier.data.collectiveBudget
import com.pineapple.budgetnotifier.data.loadData
import com.pineapple.budgetnotifier.data.selected


// todo
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(
    view: MutableState<Views>,
    navController: NavController,
) {
    val selectedBudget = remember { mutableStateOf(collectiveBudget) }
    Column(
        modifier = Modifier
    ) {
        BudgetSection(selectedBudget)
        ExpensesSection(selectedBudget, view, navController)
    }
}

// todo
@Composable
fun BudgetSection(budget: MutableState<Budget>) {
    Column(modifier = Modifier.offset(x = 80.dp, y = 20.dp)) {
        Row(modifier = Modifier.padding(10.dp)) {
            BudgetSelectionMenu(budget)
            DailyBudgetToggle(budget)
        }
        BudgetInfo(budget)
    }
}


// todo
// shows brief details about current selected budget
@Composable
fun BudgetInfo(budget: MutableState<Budget>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val budgetLeft = budget.value.limit - budget.value.totalSpent
        if (budgetLeft > 0) Text("+", modifier = Modifier.padding(20.dp))
        else Text("-", modifier = Modifier.padding(20.dp))
        Text(
            (budget.value.limit - budget.value.totalSpent).toString(),
            modifier = Modifier.padding(20.dp)
        )
        Column() {
            Text(budget.value.limit.toString())
            Text("-" + budget.value.totalSpent.toString())
        }
    }
}


// todo
// toggles between daily budget (if available) and total budget
@Composable
fun DailyBudgetToggle(budget: MutableState<Budget>) {
    Text(modifier = Modifier.clickable(onClick = {}), text = "D")
}

// todo
@Composable
fun BudgetSelectionMenu(budget: MutableState<Budget>) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
    ) {
        Row {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(painterResource(R.drawable.baseline_arrow_drop_down_24), "Select Budget")
            }
            Text(budget.value.name)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            activeBudgets.forEach { _budget ->
                DropdownMenuItem(
                    text = { Text(_budget.name) },
                    onClick = { budget.value = _budget; expanded = !expanded; }
                )
            }
        }
    }
}


// todo
@Composable
fun ExpensesSection(budget: MutableState<Budget>, view: MutableState<Views>, navController: NavController) {
    val _budget = Budget(budget.value)
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .padding(10.dp)
            .offset(x = 20.dp)
            .height(500.dp)
    ) {
        Row() {
            IconButton(onClick = {
                selected.budget = budget.value
                view.value = Views.EXPENSEINFO
                navController.navigate(Views.EXPENSEINFO.name)
            }) {
                Icon(painterResource(R.drawable.baseline_add_24), "Add Expense")
            }
            Text("Search")
        }
        LazyColumn(
            modifier = Modifier,
            state = listState,
        ) {
            items(_budget.expenses) { expense ->
                Card {
                    Row {
                        Column {
                            Text(expense.itemName)
                            Text(expense.spentAmount.toString())
                            Text(expense.time.toString() + " " + expense.date.toString())
                        }
                        IconButton(onClick = {
                            _budget.removeExpense(expense); budget.value = _budget;
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


//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun HomePreview() {
//    Home(mutableStateOf<String>('Home'))
//}