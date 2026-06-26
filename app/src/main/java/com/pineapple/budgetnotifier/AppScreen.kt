package com.pineapple.budgetnotifier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense
import com.pineapple.budgetnotifier.view.*


@Composable
fun MainScreen(
    viewModel: MainActivityViewModel,
    navController: NavHostController = rememberNavController(),
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    
    Scaffold(
        bottomBar = { BottomBar(navBackStackEntry?.destination?.route, { route -> navController.navigate(route) }) }
    ) { innerPadding ->
	Box( modifier= Modifier.padding(innerPadding) ) {
	    NavHost(
		navController = navController,
		startDestination = Views.BUDGETLIST.name,
	    ) {

		// budgets
		composable(route = Views.BUDGETLIST.name) {
		    BudgetsView(uiState.budgets,
				onBudgetClick = { budgetId ->
				    navController.navigate(Views.BUDGETINFO.name + "/${budgetId}")
				})
		}
		composable(route = Views.BUDGETEDIT.name + "/{budgetId}") { backStackEntry ->

		    val budgetId = backStackEntry.arguments?.getString("budgetId")?.toLongOrNull()
		    val budget = uiState.getBudgetByIdOrNew(budgetId)

		    BudgetEditView(budget, { updateBudget -> viewModel.addBudget(updateBudget) })
		}

		composable(route = Views.BUDGETINFO.name + "/{budgetId}") { backStackEntry ->

		    val budgetId = backStackEntry.arguments?.getString("budgetId")?.toLongOrNull()
		    val budget = uiState.getBudgetByIdOrNew(budgetId)

		    BudgetInfoView(budget = budget,
				   expenses =  uiState.expenses,
				   onBudgetEditClick = { budgetId ->
				       navController.navigate(Views.BUDGETEDIT.name +
								  "/${budgetId}")
				   },
				   onExpenseClick = { expenseId, budgetId ->
				       val expense = uiState.getExpenseByIdOrNew(expenseId, budgetId)
				       navController.navigate(Views.EXPENSEEDIT.name +
								  "/${expense.id}")
				   }
		    )
		}

		// expenses
		composable(route = Views.EXPENSELIST.name) {
		    ExpensesView(uiState.expenses,
				 onExpenseClick = { expenseId ->
				     navController.navigate(Views.EXPENSEEDIT.name + "/${expenseId}")
				 })
		}

		composable(route = Views.EXPENSEEDIT.name + "/{expenseId}") { backStackEntry ->

		    val expenseId = backStackEntry.arguments?.getString("expenseId")?.toLongOrNull()
		    val expenseOrNull = uiState.getExpenseByIdOrNull(expenseId)
		    val expense = uiState.getExpenseByIdOrNew(expenseOrNull?.id, expenseOrNull?.budgetId)



		    ExpenseEditView(expense, { updateExpense -> viewModel.addExpense(updateExpense) })
		}

		//other
		composable(route = Views.SETTINGS.name) {
		    SettingsView()
		}
	    }
	}
    }
}

@Composable
fun BottomBar(currentRoute: String?, navigate: (String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
		navigate(Views.SETTINGS.name)
            }) {
            Icon(painterResource(R.drawable.baseline_settings_24), "Settings View")
        }

	Text(currentRoute ?: "idk")

        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
		if (currentRoute == Views.BUDGETLIST.name)
		    navigate(Views.EXPENSELIST.name)
		else
		    navigate(Views.BUDGETLIST.name)
            }) {
            Icon(painterResource(R.drawable.baseline_arrow_forward_ios_24), "Switch to view")
        }

    }
}
