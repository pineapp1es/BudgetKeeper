package com.pineapple.budgetnotifier

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pineapple.budgetnotifier.Views
import com.pineapple.budgetnotifier.view.BudgetInfoView
import com.pineapple.budgetnotifier.view.BudgetsView
import com.pineapple.budgetnotifier.view.ExpenseInfoView
import com.pineapple.budgetnotifier.view.ExpensesView
import com.pineapple.budgetnotifier.view.Home
import com.pineapple.budgetnotifier.view.SettingsView
import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase
import android.content.Context

import com.pineapple.budgetnotifier.database.Selected
import com.pineapple.budgetnotifier.database.entities.Expense


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    view: MutableState<Views>,
    context: Context,
    navController: NavHostController = rememberNavController(),
) {

    val db = BudgetNotifierDatabase.getDb(context)
    Scaffold(
        bottomBar = { BottomBar(view, navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Views.HOME.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = Views.HOME.name) {
                Home(view, navController, db)
            }

            composable(route = Views.BUDGETLIST.name) {
                BudgetsView()
            }
            composable(route = Views.BUDGETINFO.name) {
                BudgetInfoView()
            }

            composable(route = Views.EXPENSELIST.name) {
                ExpensesView(view, navController)
            }
            composable(route = Views.EXPENSEINFO.name) {
                if (Selected.expense == null) Selected.expense =
						  Expense.newExpense(Selected.budget?.id
									 ?: "?budget?")
                ExpenseInfoView(view, Selected.expense!!)
            }

            composable(route = Views.SETTINGS.name) {
                SettingsView()
            }
        }
    }
}

@Composable
fun BottomBar(currentView: MutableState<Views>, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
                if (currentView.value != Views.HOME) {
                    currentView.value = Views.HOME
                    navController.navigate(Views.HOME.name)
                }
            }) {
            Icon(painterResource(R.drawable.baseline_home_24), "Home View")
        }

        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
                if (currentView.value != Views.BUDGETLIST) {
                    currentView.value = Views.BUDGETLIST
                    navController.navigate(Views.BUDGETLIST.name)
                }
            }) {
            Icon(painterResource(R.drawable.baseline_account_balance_wallet_24), "Budgets View")
        }

        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
                if (currentView.value != Views.EXPENSELIST) {
                    currentView.value = Views.EXPENSELIST
                    navController.navigate(Views.EXPENSELIST.name)
                }
            }) {
            Icon(painterResource(R.drawable.baseline_shopping_cart_24), "Transactions View")
        }

        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
                if (currentView.value != Views.SETTINGS) {
                    currentView.value = Views.SETTINGS
                    navController.navigate(Views.SETTINGS.name)
                }
            }) {
            Icon(painterResource(R.drawable.baseline_settings_24), "Settings View")
        }
    }
}
