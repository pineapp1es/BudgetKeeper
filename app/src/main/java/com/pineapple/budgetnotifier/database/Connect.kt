package com.pineapple.budgetnotifier.database

import android.content.Context
import androidx.room.Room

data class DatabaseConnection (
    val appContext: Context,
    val db: BudgetNotifierDatabase = Room.databaseBuilder(
            appContext,
            BudgetNotifierDatabase::class.java, "budget-notifier"
        ).build()
)