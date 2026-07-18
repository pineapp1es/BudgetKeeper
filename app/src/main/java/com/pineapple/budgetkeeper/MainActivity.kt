package com.pineapple.budgetkeeper

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.pineapple.budgetkeeper.MainScreen
import com.pineapple.budgetkeeper.Views
import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.style.BudgetNotifierTheme
import com.pineapple.budgetkeeper.container.BudgetKeeperContainer

class MainActivity : ComponentActivity() {

    private val database by lazy {
        BudgetNotifierDatabase.getDb(this)
    }

    private val container by lazy {
        BudgetKeeperContainer(database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BudgetNotifierTheme {
                MainScreen(container)
            }
        }
    }
}
