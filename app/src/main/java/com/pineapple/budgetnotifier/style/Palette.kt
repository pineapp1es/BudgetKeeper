package com.pineapple.budgetnotifier.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

// todo
// theme for the app
@Composable
fun BudgetNotifierTheme(content: @Composable (() -> Unit)) {
    MaterialTheme {
        content()
    }
}