package com.pineapple.budgetnotifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pineapple.budgetnotifier.style.BudgetNotifierTheme
import com.pineapple.budgetnotifier.view.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BudgetNotifierTheme {
                Home()
            }
        }
    }
}