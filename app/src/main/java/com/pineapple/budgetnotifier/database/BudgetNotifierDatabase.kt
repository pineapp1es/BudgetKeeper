package com.pineapple.budgetnotifier.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pineapple.budgetnotifier.database.access.BudgetDao
import com.pineapple.budgetnotifier.database.access.ExpenseDao
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense

@Database(entities = [Budget::class, Expense::class], version = 1)
abstract class BudgetNotifierDatabase(): RoomDatabase() {
    abstract fun budgetDao(): BudgetDao
    abstract fun expenseDao(): ExpenseDao
}