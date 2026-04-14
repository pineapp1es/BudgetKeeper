package com.pineapple.budgetnotifier.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime
import kotlin.collections.forEach


val activeBudgets: MutableList<Budget> = mutableListOf()
val expiredBudgets: MutableList<Budget> = mutableListOf()
// store info for all active budgets somehow todo
var collectiveBudget: Budget = Budget(
        "collective", "All Budgets", "All budgets in one", 0.0, 0.0, null, null
    )

// default budget where all expenses are put by default
//lateinit var defaultBudget: Budget


// todo
// function that loads all budgets from database into the variables
@RequiresApi(Build.VERSION_CODES.O)
fun loadData() {
    // placeholder data
    val placeholders = placeholderBudgets()

    activeBudgets.addAll(placeholders)


    if (activeBudgets.size > 0) {
        var collectiveLimit: Double = collectiveBudget.limit
        var collectiveSpent: Double = collectiveBudget.totalSpent
        activeBudgets.forEach { budget ->
            collectiveLimit += budget.limit
            budget.expenses.forEach { expense ->
                collectiveBudget.addExpense(expense)
            }
        }
        collectiveBudget.updateLimit(collectiveLimit)

        activeBudgets.add(collectiveBudget)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun placeholderBudgets(): MutableList<Budget> {
    val placeholders: MutableList<Budget> = mutableListOf()
    val budget1: Budget = Budget(
            "1", "BudgetOne", "BudgetOne Description", 1000.0, 500.0, LocalDate.now(), LocalDate.now()
        )
    val budget2: Budget = Budget(
            "2", "BudgetTwo", "BudgetTwo Description", 2000.0, 1000.0, LocalDate.now(), LocalDate.now()
        )
    val budget3: Budget = Budget(
            "3", "BudgetThree", "BudgetThree Description", 4000.0, 2000.0, LocalDate.now(), LocalDate.now()
        )
    val budget4: Budget = Budget(
            "4", "BudgetFour", "BudgetFour Description", 8000.0, 4000.0, LocalDate.now(), LocalDate.now()
        )

    budget1.addExpense(Expense("1", "OneOne", 300.0, LocalTime.now(), LocalDate.now(), "Expense one in budget one"))

    budget2.addExpense(Expense("2", "TwoOne", 100.0, LocalTime.now(), LocalDate.now(), "Expense one in budget two"))
    budget2.addExpense(Expense("2", "TwoTwo", 200.0, LocalTime.now(), LocalDate.now(), "Expense two in budget two"))

    budget3.addExpense(Expense("3", "ThreeOne", 200.0, LocalTime.now(), LocalDate.now(), "Expense one in budget three"))
    budget3.addExpense(Expense("3", "ThreeTwo", 400.0, LocalTime.now(), LocalDate.now(), "Expense two in budget three"))
    budget3.addExpense(Expense("3", "ThreeThree", 800.0, LocalTime.now(), LocalDate.now(), "Expense three in budget three"))

    budget4.addExpense(Expense("4", "FourOne", 100.0, LocalTime.now(), LocalDate.now(), "Expense one in budget four"))
    budget4.addExpense(Expense("4", "FourTwo", 200.0, LocalTime.now(), LocalDate.now(), "Expense two in budget four"))
    budget4.addExpense(Expense("4", "FourThree", 400.0, LocalTime.now(), LocalDate.now(), "Expense three in budget four"))
    budget4.addExpense(Expense("4", "FourFour", 800.0, LocalTime.now(), LocalDate.now(), "Expense four in budget four"))

    placeholders.addAll(
        listOf(
            budget1,
            budget2,
            budget3,
            budget4,
        )
    )

    return placeholders
}