package com.pineapple.budgetnotifier.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


// todo
@Composable
fun Home() {
    Column {
        BudgetInfo()
        ListOfExpenses()
    }
}

// todo
@Composable
fun BudgetInfo() {
    Card {
        Text("Budget Here")
    }
}

// todo
@Composable
fun BudgetSelectionMenu() {

}


// todo
@Composable
fun ListOfExpenses() {
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

@Preview
@Composable
fun HomePreview() {
    Home()
}