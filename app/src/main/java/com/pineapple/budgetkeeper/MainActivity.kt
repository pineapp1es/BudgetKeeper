package com.pineapple.budgetkeeper

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.pineapple.budgetkeeper.MainScreen
import com.pineapple.budgetkeeper.Views
import com.pineapple.budgetkeeper.database.BudgetNotifierDatabase
import com.pineapple.budgetkeeper.database.BudgetRepository
import com.pineapple.budgetkeeper.style.BudgetNotifierTheme

class MainActivity : ComponentActivity() {

    private val database by lazy {
        BudgetNotifierDatabase.getDb(this)
    }

    private val repository by lazy {
        BudgetRepository(database)
    }

    private val viewModel: MainActivityViewModel by viewModels() {
	MainActivityViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val view = remember { mutableStateOf(Views.BUDGETLIST) }
            BudgetNotifierTheme {
                MainScreen(viewModel)
            }
        }
    }
}
