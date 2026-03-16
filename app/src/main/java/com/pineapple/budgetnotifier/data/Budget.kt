package com.pineapple.budgetnotifier.data

import java.time.LocalDate


data class Budget(
    var id: String, // unique identifier for budget
    var name: String, // name for budget
    var desc: String, // description for budget
    var limit: Double, // limit set
    var totalSpent: Double, // spent
    val startDate: LocalDate?, // start day for the budget
    var endDate: LocalDate?, // number of days budget is set for
) {

    val expired: Boolean = false // stores flag for if budget has expired (ended)
    val expenses: MutableList<Expense> = mutableListOf() // list of expenses
    val limitHistory: MutableList<Double> = mutableListOf() // history of limits set for this budget

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
        this.expenses.add(expense)
    }
}