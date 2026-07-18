package com.pineapple.budgetkeeper.view

import com.pineapple.budgetkeeper.R
import com.pineapple.budgetkeeper.Views
import com.pineapple.budgetkeeper.components.BudgetCard
import com.pineapple.budgetkeeper.viewmodel.BudgetListViewModel
import com.pineapple.budgetkeeper.uistate.BudgetListUiState

import java.util.Date

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import com.pineapple.budgetkeeper.database.entities.Budget
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BudgetsView(
    uiState: BudgetListUiState,
    onNewClick: () -> Unit,
    onBudgetClick: (Budget) -> Unit,
    onBudgetDelete: (Budget) -> Unit,
    onBudgetHold: (Budget) -> Unit,
) {


    Box(
	modifier = Modifier
	    .padding(30.dp),
    ) {
	Column {
	    IconButton(
		modifier = Modifier,
		onClick = { onNewClick() }
	    ) {
		Icon(painterResource(R.drawable.baseline_add_24), "New budget")
	    }


	    LazyColumn(
		modifier = Modifier
	    ) {

		stickyHeader {
		    Text("Active")
		}
		items(uiState.activeBudgets) { budget ->
		    BudgetCard(
			budget = budget,
			onClick = onBudgetClick,
			onDelete = onBudgetDelete,
                        onHold = onBudgetHold,
		    )
		}

		stickyHeader {
		    Text("Future")
		}
		items(uiState.futureBudgets) { budget ->
		    BudgetCard(
			budget = budget,
			onClick = onBudgetClick,
			onDelete = onBudgetDelete,
                        onHold = onBudgetHold,
		    )
		}

		stickyHeader {
		    Text("Inactive")
		}
		items(uiState.inactiveBudgets) { budget ->
		    BudgetCard(
			budget = budget,
			onClick = onBudgetClick,
			onDelete = onBudgetDelete,
                        onHold = onBudgetHold,
		    )
		}


	    }

	}
    }
}

