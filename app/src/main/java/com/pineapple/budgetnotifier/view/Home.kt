package com.pineapple.budgetnotifier.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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


// todo
@Composable
fun Home() {
    var selectedBudget: Budget? = null
    Column {
        BudgetSection(selectedBudget)
        ExpensesSection(selectedBudget)
        BottomBar("Home")
    }
}

// todo
@Composable
fun BudgetSection(budget: Budget?) {
    Column {
        BudgetSelectionMenu()
    }
}

// todo
@Composable
fun BudgetSelectionMenu() {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Option 1") },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Option 2") },
                onClick = { /* Do something... */ }
            )
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


@Preview
@Composable
fun HomePreview() {
    Home()
}