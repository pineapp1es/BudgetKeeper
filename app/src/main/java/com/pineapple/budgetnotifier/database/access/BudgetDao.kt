package com.pineapple.budgetnotifier.database.access

import androidx.room.*
import com.pineapple.budgetnotifier.database.entities.Budget
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

    @Query("SELECT * FROM budget")
    fun getAllBudgets(): Flow<Array<Budget>>
}
