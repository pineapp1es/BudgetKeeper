package com.pineapple.budgetnotifier

import com.pineapple.budgetnotifier.database.Selected
import com.pineapple.budgetnotifier.database.cacheAllBudgetData
import com.pineapple.budgetnotifier.database.entities.Expense
import com.pineapple.budgetnotifier.view.*
import com.pineapple.budgetnotifier.database.BudgetNotifierDatabase

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    view: MutableState<Views>,
    context: Context,
    navController: NavHostController = rememberNavController(),
) {

    val db = BudgetNotifierDatabase.getDb(context)

    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
	cacheAllBudgetData(context)
    }
    
    Scaffold(
        bottomBar = { BottomBar(view, navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Views.BUDGETLIST.name,
        ) {

            composable(route = Views.BUDGETLIST.name) {
                BudgetsView(navController)
            }
            composable(route = Views.BUDGETINFO.name) {
                BudgetInfoView(db)
            }

            composable(route = Views.EXPENSELIST.name) {
                ExpensesView()
            }
            composable(route = Views.EXPENSEINFO.name) {
                ExpenseInfoView()
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
            }) {
            Icon(painterResource(R.drawable.baseline_settings_24), "Settings View")
        }

	Text("Budgets")

        IconButton(
            modifier = Modifier
                .padding(20.dp),
            onClick = {
            }) {
            Icon(painterResource(R.drawable.baseline_arrow_forward_ios_24), "Switch to view")
        }

    }
}
