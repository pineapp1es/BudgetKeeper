package com.pineapple.budgetnotifier.database.access

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pineapple.budgetnotifier.database.entities.Budget

@Dao
interface BudgetDao {

    @Insert
    fun insertBudgets(vararg budgets: Budget)

    @Update
    fun updateBudgets(vararg budgets: Budget)

    @Delete
    fun deleteBudgets(vararg budgets: Budget)

    @Query("SELECT * FROM budget")
    fun loadAllBudgets(): Array<Budget>
}