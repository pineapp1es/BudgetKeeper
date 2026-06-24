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

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
	Box( modifier= Modifier.padding(innerPadding) ) {
	    NavHost(
		navController = navController,
		startDestination = Views.BUDGETLIST.name,
	    ) {

		// budgets
		composable(route = Views.BUDGETLIST.name) {
		    BudgetsView(uiState.value.budgets,
				onBudgetClick = { budgetId ->
				    navController.navigate(Views.BUDGETINFO.name + "/${budgetId}")
				})
		}
		composable(route = Views.BUDGETINFO.name + "/{budgetId}") { backStackEntry ->

		    val budgetId = backStackEntry.arguments?.getString("budgetId")?.toLongOrNull()

		    val budget: Budget = uiState.value.budgets.find { it.id == budgetId }
			?: Budget.newBudget()

		    BudgetInfoView(budget, { updateBudget -> viewModel.addBudget(updateBudget) })
		}

		// expenses
		composable(route = Views.EXPENSELIST.name) {
		    ExpensesView(uiState.value.expenses,
				 onExpenseClick = { expenseId ->
				     navController.navigate(Views.EXPENSEINFO.name + "/${expenseId}")
				 })
		}
		    composable(route = Views.EXPENSEINFO.name + "/{expenseId}") { backStackEntry ->
		    val expenseId = backStackEntry.arguments?.getString("expenseId")?.toLongOrNull()

		    val expense: Expense = uiState.value.expenses.find { it.id == expenseId }
			?: Expense.newExpense()

		    ExpenseInfoView(expense,
{ updateExpense -> viewModel.addExpense(updateExpense) })
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
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
            }) {
            Icon(painterResource(R.drawable.baseline_settings_24), "Settings View")
        }

	Text(currentRoute ?: "idk")

        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
		if (currentRoute == Views.BUDGETLIST.name)
		    navController.navigate(Views.EXPENSELIST.name)
		else
		    navController.navigate(Views.BUDGETLIST.name)
            }) {
            Icon(painterResource(R.drawable.baseline_arrow_forward_ios_24), "Switch to view")
        }

    }
}
