package com.pineapple.budgetnotifier.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun BudgetNotifierTheme(content: @Composable (() -> Unit)) {
    MaterialTheme {
        content()
    }
}