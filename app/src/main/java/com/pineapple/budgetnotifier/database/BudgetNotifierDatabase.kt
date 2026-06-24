package com.pineapple.budgetnotifier.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pineapple.budgetnotifier.database.access.BudgetDao
import com.pineapple.budgetnotifier.database.access.ExpenseDao
import com.pineapple.budgetnotifier.database.entities.Budget
import com.pineapple.budgetnotifier.database.entities.Expense
import com.pineapple.budgetnotifier.database.Converters
import android.content.Context
import androidx.room.Room
import androidx.room.TypeConverters
import kotlin.synchronized

@Database(entities = [Budget::class, Expense::class], version = 1)
@TypeConverters(Converters::class)
abstract class BudgetNotifierDatabase(): RoomDatabase() {
    abstract fun budgetDao(): BudgetDao
    abstract fun expenseDao(): ExpenseDao

    companion object {

        @Volatile
        private var db: BudgetNotifierDatabase? = null

        fun getDb(context: Context): BudgetNotifierDatabase {

            return db ?: synchronized(this) {

                db ?: Room.databaseBuilder(
                    context.applicationContext,
                    BudgetNotifierDatabase::class.java, "budget-notifier"
                ).build().also {
		    db = it
		}
            }

        }
    }
}
