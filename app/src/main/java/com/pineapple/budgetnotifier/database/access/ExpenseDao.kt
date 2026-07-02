package com.pineapple.budgetnotifier.database.access

import androidx.room.*
import com.pineapple.budgetnotifier.database.entities.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert
    suspend fun insertExpenses(vararg expenses: Expense)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceExpenses(vararg expenses: Expense)

    @Update
    suspend fun updateExpenses(vararg  expenses: Expense)

    @Delete
    suspend fun deleteExpenses(vararg expenses: Expense)

    @Query("""
	       DELETE FROM Expense
	       WHERE budgetId = :budgetId
	   """)
    suspend fun deleteExpensesByBudgetId(budgetId: Long)

    @Query("SELECT * FROM expense")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expense WHERE budgetId = :budgetId")
    suspend fun getExpensesByBudgetId(budgetId: Long): List<Expense>

    @Query("SELECT * FROM expense WHERE id = :id")
    suspend fun getExpenseById(id: Long): Expense?


    @Query("""
	       SELECT COALESCE(SUM(cost), 0)
	       FROM expense
               WHERE budgetId = :budgetId
	   """)
    suspend fun getTotalSpentByBudget(budgetId: Long): Double

    @Query("""
	       UPDATE Expense
	       SET budgetId = :newBudgetId
	       WHERE budgetId = :oldBudgetId
	   """)
    suspend fun moveExpensesToBudget(oldBudgetId: Long, newBudgetId: Long)

    @Query("""
	       UPDATE Expense
	       SET budgetId = :budgetId
	       WHERE id = :expenseId
	   """)
    suspend fun moveExpenseTo(expenseId: Long, budgetId: Long)
}
