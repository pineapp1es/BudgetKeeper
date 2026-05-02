package com.pineapple.budgetnotifier

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.pineapple.budgetnotifier.data.Views
import com.pineapple.budgetnotifier.style.BudgetNotifierTheme
import com.pineapple.budgetnotifier.view.ExpenseInfoView
import com.pineapple.budgetnotifier.view.ExpensesView
import com.pineapple.budgetnotifier.view.Home

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val view = remember { mutableStateOf(Views.HOME) }
            BudgetNotifierTheme {
                MainScreen(view)
            }
        }
    }
}