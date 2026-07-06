package com.pineapple.budgetkeeper.database.access

import androidx.room.*
import com.pineapple.budgetkeeper.database.entities.Budget
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    @Insert
    suspend fun insertBudgets(vararg budgets: Budget)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceBudgets(vararg budgets: Budget)

    @Update
    suspend fun updateBudgets(vararg budgets: Budget)

    @Delete
    suspend fun deleteBudgets(vararg budgets: Budget)

    @Query("""
UPDATE Budget
SET spent = spent + :amount
WHERE id = :id
	       """)
    suspend fun changeSpent(id: Long, amount: Double)

    @Query("SELECT * FROM budget")
    fun getAllBudgets(): Flow<List<Budget>>
}
