package com.pineapple.budgetnotifier.database.access

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pineapple.budgetnotifier.database.entities.Expense

@Dao
interface ExpenseDao {

    @Insert
    fun insertExpenses(vararg expenses: Expense)

    @Update
    fun updateExpenses(vararg  expenses: Expense)

    @Delete
    fun deleteExpenses(vararg expenses: Expense)

    @Query("SELECT * FROM expense")
    fun loadAllExpenses(): Array<Expense>

    @Query("SELECT * FROM expense WHERE budgetId = :budgetId")
    fun loadExpenseByBudgetId(budgetId: String): Array<Expense>
}