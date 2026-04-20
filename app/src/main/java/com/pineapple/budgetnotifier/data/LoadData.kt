package com.pineapple.budgetnotifier.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime
import kotlin.collections.forEach
import kotlin.collections.mutableListOf


val activeBudgets: MutableList<Budget> = mutableListOf()
val expiredBudgets: MutableList<Budget> = mutableListOf()

// store info for all active budgets somehow todo
var collectiveBudget: Budget = Budget(
    "collective", "All Budgets", "All budgets in one",
    0.0, 0.0,
    null, null,
    mutableListOf(), mutableListOf(),
)

val selected: SelectionData = SelectionData(collectiveBudget, null)

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
        activeBudgets.forEach { budget ->
            collectiveLimit += budget.limit
            budget.expenses.forEach { expense ->
                collectiveBudget.addExpense(expense)
            }
        }
        collectiveBudget.updateLimit(collectiveLimit)

        activeBudgets.add(collectiveBudget)
    }

    allBudgets.addAll(activeBudgets)

//    selected.expense = allBudgets[2].expenses[0]
}

@RequiresApi(Build.VERSION_CODES.O)
fun placeholderBudgets(): MutableList<Budget> {
    val placeholders: MutableList<Budget> = mutableListOf()
    val budget1: Budget = Budget(
        "1", "BudgetOne", "BudgetOne Description",
        0.0, 1000.0,
        LocalDate.now(), LocalDate.now(),
        mutableListOf(), mutableListOf(),
    )
    val budget2: Budget = Budget(
        "2", "BudgetTwo", "BudgetTwo Description",
        0.0, 2000.0,
        LocalDate.now(), LocalDate.now(),
        mutableListOf(), mutableListOf(),
    )
    val budget3: Budget = Budget(
        "3", "BudgetThree", "BudgetThree Description",
        0.0, 4000.0,
        LocalDate.now(), LocalDate.now(),
        mutableListOf(), mutableListOf(),
    )
    val budget4: Budget = Budget(
        "4", "BudgetFour", "BudgetFour Description",
        0.0, 8000.0,
        LocalDate.now(), LocalDate.now(),
        mutableListOf(), mutableListOf(),
    )
    val budget5: Budget = Budget(
        "5", "BudgetFive", "BudgetFive Description",
        0.0, 16000.0,
        LocalDate.now(), LocalDate.now(),
        mutableListOf(), mutableListOf(),
    )

    budget1.addExpense(
        Expense(
            mutableListOf(),
            "OneOne",
            300.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense one in budget one"
        )
    )

    budget2.addExpense(
        Expense(
            mutableListOf(),
            "TwoOne",
            100.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense one in budget two"
        )
    )
    budget2.addExpense(
        Expense(
            mutableListOf(),
            "TwoTwo",
            200.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense two in budget two"
        )
    )

    budget3.addExpense(
        Expense(
            mutableListOf(),
            "ThreeOne",
            200.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense one in budget three"
        )
    )
    budget3.addExpense(
        Expense(
            mutableListOf(),
            "ThreeTwo",
            400.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense two in budget three"
        )
    )
    budget3.addExpense(
        Expense(
            mutableListOf(),
            "ThreeThree",
            800.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense three in budget three"
        )
    )

    budget4.addExpense(
        Expense(
            mutableListOf(),
            "FourOne",
            100.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense one in budget four"
        )
    )
    budget4.addExpense(
        Expense(
            mutableListOf(),
            "FourTwo",
            200.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense two in budget four"
        )
    )
    budget4.addExpense(
        Expense(
            mutableListOf(),
            "FourThree",
            400.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense three in budget four"
        )
    )
    budget4.addExpense(
        Expense(
            mutableListOf(),
            "FourFour",
            800.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense four in budget four"
        )
    )


    budget5.addExpense(
        Expense(
            mutableListOf(),
            "FiveOne",
            100.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense one in budget five"
        )
    )
    budget5.addExpense(
        Expense(
            mutableListOf(),
            "FiveTwo",
            200.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense two in budget five"
        )
    )
    budget5.addExpense(
        Expense(
            mutableListOf(),
            "FiveThree",
            400.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense three in budget five"
        )
    )
    budget5.addExpense(
        Expense(
            mutableListOf(),
            "FiveFour",
            800.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense four in budget five"
        )
    )
    budget5.addExpense(
        Expense(
            mutableListOf(),
            "FiveFive",
            1600.0,
            LocalTime.now(),
            LocalDate.now(),
            "Expense five in budget five"
        )
    )


    placeholders.addAll(
        listOf(
            budget1,
            budget2,
            budget3,
            budget4,
            budget5,
        )
    )

    return placeholders
}