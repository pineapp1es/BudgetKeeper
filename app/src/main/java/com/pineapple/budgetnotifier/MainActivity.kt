package com.pineapple.budgetnotifier

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

import com.pineapple.budgetnotifier.MainScreen
import com.pineapple.budgetnotifier.Views
import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase
import com.pineapple.budgetnotifier.database.BudgetRepository
import com.pineapple.budgetnotifier.style.BudgetNotifierTheme
import com.pineapple.budgetnotifier.view.ExpenseInfoView
import com.pineapple.budgetnotifier.view.ExpensesView

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
	    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            BudgetNotifierTheme {
                MainScreen(uiState)
            }
        }
    }
}
