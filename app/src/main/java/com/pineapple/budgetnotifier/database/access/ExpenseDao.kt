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

    @Query("SELECT * FROM expense")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expense WHERE budgetId = :budgetId")
    fun getExpensesByBudgetId(budgetId: String): Flow<List<Expense>>
}
