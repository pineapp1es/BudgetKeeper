package com.pineapple.budgetnotifier.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


val activeBudgets: MutableList<Budget> = mutableListOf()
val expiredBudgets: MutableList<Budget> = mutableListOf()
// store info for all active budgets somehow todo
var collectiveBudget: Budget? = null
// default budget where all expenses are put by default
//lateinit var defaultBudget: Budget


// todo
// function that loads all budgets from database into the variables
@RequiresApi(Build.VERSION_CODES.O)
fun loadData() {
    // placeholder data

    activeBudgets.add(
        Budget(
            "1", "BudgetOne", "BudgetOne Description", 1000.0, 500.0, LocalDate.now(), LocalDate.now()
        ),
    )

    var collectiveLimit: Double = 0.0
    var collectiveSpent: Double = 0.0
    activeBudgets.forEach { budget ->
        collectiveLimit += budget.limit
        collectiveSpent += budget.totalSpent
    }

    collectiveBudget = Budget(
        "collective", "All Budgets", "All budgets in one", collectiveLimit, collectiveSpent, null, null
    )

}