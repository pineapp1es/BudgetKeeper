package com.pineapple.budgetnotifier.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pineapple.budgetnotifier.data.Budget
import com.pineapple.budgetnotifier.data.activeBudgets
import com.pineapple.budgetnotifier.data.collectiveBudget
import com.pineapple.budgetnotifier.data.loadData




// todo
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home() {
    var view by remember { mutableStateOf("Home") }
    val selectedBudget = remember { mutableStateOf(collectiveBudget) }
    loadData()
    Column (
        modifier = Modifier
            .padding(20.dp)
    ) {
        BudgetSection(selectedBudget)
        ExpensesSection(selectedBudget)
        BottomBar(view)
    }
}

// todo
@Composable
fun BudgetSection(budget: MutableState<Budget>) {
    Column {
        Row(modifier = Modifier.padding(10.dp)) {
            BudgetSelectionMenu(budget)
            DailyBudgetToggle(budget)
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
//            .padding(16.dp)
    ) {
        Row {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Budget")
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
fun ExpensesSection(selected: MutableState<Budget>) {
    Column {
        Text("Search")
        LazyColumn {
            items(selected.value.expenses) { expense ->
                Card {
                    Row {
                        Column() {
                            Text(expense.itemName)
                            Text(expense.spentAmount.toString())
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete Expense")
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.MoreVert, contentDescription = "About Expense")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(currentView: String) {

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomePreview() {
    Home()
}