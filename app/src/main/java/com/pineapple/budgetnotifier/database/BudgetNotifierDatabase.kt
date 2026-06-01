package com.pineapple.budgetnotifier.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pineapple.budgetnotifier.database.access.BudgetDao
import com.pineapple.budgetnotifier.database.access.ExpenseDao
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense
import android.content.Context
import kotlin.synchronized

@Database(entities = [Budget::class, Expense::class], version = 1)
abstract class BudgetNotifierDatabase(): RoomDatabase() {
    abstract fun budgetDao(): BudgetDao
    abstract fun expenseDao(): ExpenseDao

    companion object {

        @Volatile
        private var db: BudgetNotifierDatabase? = null

        fun getDb(context: Context): BudgetNotifierDatabase {

            return db ?: synchronized(this) {

                val newdb: BudgetNotifierDatabase = Room.databaseBuilder(
                    context,
                    BudgetNotifierDatabase::class.java, "budget-notifier"
                ).build()
                db = newdb

                db
            }

        }
    }
}
