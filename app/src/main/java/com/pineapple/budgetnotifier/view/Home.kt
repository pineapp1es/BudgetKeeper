package com.pineapple.budgetnotifier.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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



var selectedBudget: Budget? = collectiveBudget
// todo
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home() {
    Column {
        BudgetSection()
        ExpensesSection(selectedBudget)
        BottomBar("Home")
    }
}

// todo
@Composable
fun BudgetSection() {
    Column {
        Row(modifier = Modifier.padding(10.dp)) {
            BudgetSelectionMenu()
            DailyBudgetToggle()
        }
    }
}

// todo
// toggles between daily budget (if available) and total budget
@Composable
fun DailyBudgetToggle() {
    Text(modifier = Modifier.clickable(onClick = {}), text = "D")
}

// todo
@Composable
fun BudgetSelectionMenu() {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
//            .padding(16.dp)
    ) {
        Row {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "More options")
            }
            Text(selectedBudget?.name ?: "test")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            activeBudgets.forEach { budget ->
                DropdownMenuItem(
                    text = { Text(budget.name) },
                    onClick = { selectedBudget = budget }
                )
            }
        }
    }
}


// todo
@Composable
fun ExpensesSection(budget: Budget?) {
    Column {
        Text("Search")
        Card {
            Text("Expense#1")
        }
        Card {
            Text("Expense#2")
        }
        Card {
            Text("Expense#3")
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