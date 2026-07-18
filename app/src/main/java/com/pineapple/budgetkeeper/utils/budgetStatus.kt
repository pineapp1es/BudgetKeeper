package com.pineapple.budgetkeeper.utils

import com.pineapple.budgetkeeper.database.entities.Budget

import java.util.Date

enum class BudgetStatus {
    ACTIVE,
    INACTIVE,
    FUTURE,
}

fun Budget.status(now: Date): BudgetStatus = 
    when {
        (endDate.getTime() < now) -> BudgetStatus.INACTIVE
        (startDate.getTime() > now) -> BudgetStatus.FUTURE
        else -> BudgetStatus.ACTIVE
    }
