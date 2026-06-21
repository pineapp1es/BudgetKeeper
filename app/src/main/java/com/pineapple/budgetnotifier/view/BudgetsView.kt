package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.Views

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import com.pineapple.budgetnotifier.database.entities.Budget
import androidx.compose.foundation.lazy.items

@Composable
fun BudgetsView(budgets: List<Budget>, navController: NavHostController) {

    Box(
	modifier = Modifier
	    .padding(30.dp),
    ) {
	Column {
	    IconButton(
		modifier = Modifier,
		onClick = {
		    navController.navigate(Views.BUDGETINFO.name + "/")
		}
	    ) {
		Icon(painterResource(R.drawable.baseline_add_24), "New budget")
	    }


	    LazyColumn(
		modifier = Modifier
	    ) {

		items(budgets) { budget ->
		    Card (
			modifier = Modifier,
			onClick = {
			    navController.navigate(Views.BUDGETINFO.name + "/${budget.id}")
			}
		    ) {
			Text(budget.name)
		    }
		}

	    }

	}
    }
}
