package com.pineapple.budgetkeeper

import com.pineapple.budgetkeeper.database.entities.Budget
import com.pineapple.budgetkeeper.database.entities.Expense
import com.pineapple.budgetkeeper.container.BudgetKeeperContainer
import com.pineapple.budgetkeeper.viewmodel.*
import com.pineapple.budgetkeeper.view.*

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

import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MainScreen(
    container: BudgetKeeperContainer,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    
    Scaffold(
        bottomBar = {
            BottomBar(navBackStackEntry?.destination?.route)
            { route -> navController.navigate(route) }
        }
    ) { innerPadding ->
        Box( modifier= Modifier.padding(innerPadding) ) {
            NavHost(
                navController = navController,
                startDestination = Views.BUDGETLIST.name,
            ) {

                // BUDGET LIST SCREEN ------------------------------------------------------------
                composable(route = Views.BUDGETLIST.name) {
                    val vm: BudgetListViewModel = viewModel(
                        factory = BudgetKeeperViewModelFactory {
                            BudgetListViewModel(
                                container.budgetRepo,
                                container.expenseRepo,
                                container.budgetDeleteUseCase,
                            )
                        }
                    )

                    val uiState by vm.uiState.collectAsStateWithLifecycle()
                    BudgetsView(
                        uiState = uiState,

                        onNewClick = {
                        },
                        onBudgetClick = { budget ->
                        },
                        onBudgetDelete = { budget ->
                        },
                        onBudgetHold = { budget ->
                        }
                    )
                }
                // -------------------------------------------------------------------------------

                // BUDGET INFO SCREEN ------------------------------------------------------------
                composable(route = Views.BUDGETINFO.name + "/{budgetId}") { backStackEntry ->
                    val budgetId = backStackEntry.arguments?.getString("budgetId")!!.toLong()
                    val vm: BudgetInfoViewModel = viewModel(
                        factory = BudgetKeeperViewModelFactory {
                            BudgetInfoViewModel(
                                budgetId,
                                container.budgetRepo,
                                container.expenseRepo,
                                container.budgetDeleteUseCase,
                            )
                        }
                    )

                    val uiState by vm.uiState.collectAsStateWithLifecycle()
                    BudgetInfoView(
                        uiState = uiState,
                        onBudgetEdit = {budget -> },
                        onBudgetDelete = {budget -> },
                        onNewExpenseClick = { },
                        onExpenseClick = {expense -> },
                        onExpenseHold = {expense -> },
                        onExpenseDelete = {expense-> },
                    )
                }
                // -------------------------------------------------------------------------------

                // BUDGET EDIT SCREEN ------------------------------------------------------------
                composable(route = Views.BUDGETEDIT.name + "/{budgetId}") { backStackEntry ->
                    val budgetId = backStackEntry.arguments?.getString("budgetId")!!.toLong()
                    val vm: BudgetEditViewModel = viewModel(
                        factory = BudgetKeeperViewModelFactory {
                            BudgetEditViewModel(
                                budgetId,
                                container.budgetRepo,
                                container.budgetDeleteUseCase,
                            )
                        }
                    )

                    val uiState by vm.uiState.collectAsStateWithLifecycle()
                    BudgetEditView(
                        uiState = uiState,
                        onSave = {budget -> },
                        onDelete = {budget -> },
                        isNew = false,
                    )
                }
                // -------------------------------------------------------------------------------

                // EXPENSE LIST SCREEN -----------------------------------------------------------
                composable(route = Views.EXPENSELIST.name) {
                    val vm: ExpenseListViewModel = viewModel(
                        factory = BudgetKeeperViewModelFactory {
                            ExpenseListViewModel(
                                container.budgetRepo,
                                container.expenseRepo,
                            )
                        }
                    )

                    val uiState by vm.uiState.collectAsStateWithLifecycle()
                    ExpensesView(
                        uiState = uiState,
                        onExpenseClick = {expense -> },
                        onExpenseHold = {expense -> },
                        onExpenseDelete = {expense -> },
                        onNewExpenseClick = { },
                    )
                }
                // -------------------------------------------------------------------------------

                // EXPENSE EDIT SCREEN -----------------------------------------------------------
                composable(route = Views.EXPENSEEDIT.name + "/{expenseId}") { backStackEntry ->
                    val expenseId = backStackEntry.arguments?.getString("expenseId")?.toLongOrNull()
                    val vm: ExpenseEditViewModel = viewModel(
                        factory = BudgetKeeperViewModelFactory {
                            ExpenseEditViewModel(
                                expenseId,
                                container.budgetRepo,
                                container.expenseRepo,
                            )
                        }
                    )

                    val uiState by vm.uiState.collectAsStateWithLifecycle()
                    ExpenseEditView(
                        uiState = uiState,
                        onSave = {expense -> },
                        onDelete = {expense -> },
                    )
                }
                // -------------------------------------------------------------------------------

            //     // budgets
            //     composable(route = Views.BUDGETLIST.name) {
            //         BudgetsView(uiState.budgets,
            //                     onNewClick = { 
            //                         navController.navigate(Views.BUDGETEDIT.name + "/${null}")
            //                     },
            //                     onBudgetClick = { budget ->
            //                         navController.navigate(Views.BUDGETINFO.name + "/${budget.id}")
            //                     },
            //                     onDelete = { budget, moveToBudgetId ->
            //                         viewModel.deleteBudget(budget, moveToBudgetId)
            //                     },
            //         )
            //     }
            //     composable(route = Views.BUDGETEDIT.name + "/{budgetId}") { backStackEntry ->

            //         val budgetId = backStackEntry.arguments?.getString("budgetId")?.toLongOrNull()
            //         val budget = uiState.getBudgetByIdOrNew(budgetId)

            //         BudgetEditView(
            //             budget,
            //             onSave = { updateBudget -> viewModel.addOrUpdateBudget(updateBudget) },
            //             onDelete = { budget, moveToBudgetId ->
            //                 viewModel.deleteBudget(budget, moveToBudgetId)
            //             },
            //             isNew = budget.id == 0.toLong(),
            //         )
            //     }

            //     composable(route = Views.BUDGETINFO.name + "/{budgetId}") { backStackEntry ->

            //         val budgetId = backStackEntry.arguments?.getString("budgetId")!!.toLong()
            //         val budget = uiState.getBudgetByIdOrNew(budgetId)
            //         val expenses = uiState.getExpensesByBudgetId(budgetId)

            //         BudgetInfoView(
            //             budget = budget,
            //             expenses =  expenses,
            //             onBudgetEdit = { budget ->
            //                 navController.navigate(Views.BUDGETEDIT.name +
            //                                            "/${budget.id}")
            //             },
            //             onBudgetDelete = { budget, moveToBudgetId ->
            //                 viewModel.deleteBudget(budget, moveToBudgetId)
            //             },
            //             onExpenseClick = { expense ->
            //                 navController.navigate(Views.EXPENSEEDIT.name +
            //                                            "/${expense.id}/${expense.budgetId}")
            //             },
            //             onExpenseHold = { expense ->
            //             },
            //             onExpenseDelete = { expense ->
            //                 viewModel.deleteExpense(expense)
            //             },
            //             onNewExpenseClick = {
            //                 navController.navigate(Views.EXPENSEEDIT.name + "/${null}/${null}")
            //             }
            //         )
            //     }

            //     // expenses
            //     composable(route = Views.EXPENSELIST.name) {
            //         ExpensesView(
            //             uiState.expenses,
            //             onExpenseClick = { expense ->
            //                 navController.navigate(Views.EXPENSEEDIT.name +
            //                                            "/${expense.id}/${expense.budgetId}")
            //             },
            //             onExpenseDelete = { expense -> viewModel.deleteExpense(expense) },
            //             canCreateNew = uiState.budgets.size > 0,
            //             onExpenseHold = { expense -> },
            //             onNewExpenseClick = {
            //                 navController.navigate(Views.EXPENSEEDIT.name + "/${null}/${null}")
            //             },
            //         )
            //     }
            //     composable(route = Views.EXPENSEEDIT.name + "/{expenseId}/{budgetId}") { backStackEntry ->

            //         if (uiState.budgets.size != 0) {
            //             val expenseId = backStackEntry.arguments?.getString("expenseId")?.toLongOrNull()
            //             val budgetId = backStackEntry.arguments?.getString("budgetId")?.toLongOrNull()
            //             val expense = uiState.getExpenseByIdOrNew(expenseId, budgetId)

            //             ExpenseEditView(
            //                 expense,
            //                 uiState.budgets,
            //                 onSave = { updateExpense -> viewModel.addOrUpdateExpense(updateExpense) },
            //                 onDelete = { expense -> viewModel.deleteExpense(expense) },
            //                 isNew = expense.id == 0.toLong(),
            //             )
            //         }
            //         else navController.navigate(Views.BUDGETLIST.name)

            //     }

            //     //other
            //     composable(route = Views.SETTINGS.name) {
            //         SettingsView()
            //     }
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
