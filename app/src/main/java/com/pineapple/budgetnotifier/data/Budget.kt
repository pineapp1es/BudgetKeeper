package com.pineapple.budgetnotifier.data

import java.time.LocalDate


val allBudgets: MutableList<Budget> = mutableListOf()

data class Budget(
    var id: String, // unique identifier for budget
    var name: String, // name for budget
    var desc: String, // description for budget
    var totalSpent: Double,
    var limit: Double, // limit set
    val startDate: LocalDate?, // start day for the budget
    var endDate: LocalDate?, // number of days budget is set for
    val expenses: MutableList<Expense>,
    val limitHistory: MutableList<Double>,
    var expired: Boolean,
) {

    constructor(budget: Budget) : this(
        budget.id, budget.name, budget.desc,
        budget.totalSpent, budget.limit, budget.startDate,
        budget.endDate, budget.expenses, budget.limitHistory,
        budget.expired,
    )


    // Checks if total money spent has reached or exceeded budget
    fun hasReachedLimit(): Boolean {
        return this.totalSpent >= this.limit
    }

    // todo
    // returns the number of days left for budget to expire
    fun numberOfDaysLeft(): Int {
//        val daysLeft = endDate - stardDate
//        return daysLeft
        return 0
    }

    // todo
    // checks if budget has expired, also sets the expired flag if it has expired
    fun hasExpired(): Boolean {
        //
        return expired
    }

    // sets the current limit to a new one and adds the old one to the history
    fun updateLimit(newLimit: Double) {
        limitHistory.add(limit)
        this.limit = newLimit
    }

    // renames the budget
    fun renameBudget(newName: String) {
        // todo
        // check if budget name has already been used
        this.name = newName;
    }

    // adds a new expense to the budget
    fun addExpense(expense: Expense) {
        expense.budgetIds.add(this.id)
        this.expenses.add(expense)
        this.totalSpent += expense.spentAmount
    }

    // removes expense from budget
    fun removeExpense(expense: Expense) {
        this.expenses.remove(expense)
        expense.budgetIds.remove(this.id)
        this.totalSpent -= expense.spentAmount
        allBudgets.forEach { budget ->
            if (budget.id in expense.budgetIds) budget.removeExpense(expense);
        }
    }
}