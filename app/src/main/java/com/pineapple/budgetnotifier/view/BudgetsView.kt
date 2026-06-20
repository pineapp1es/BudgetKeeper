package com.pineapple.budgetnotifier.view

import com.pineapple.budgetnotifier.R
import com.pineapple.budgetnotifier.database.Cached
import com.pineapple.budgetnotifier.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Box


@Composable
fun BudgetsView(navController: NavHostController) {

    Box {
	Column {
	    IconButton(
		modifier = Modifier,
		onClick = {
		    navController.navigate(Views.BUDGETINFO.name)
		}
	    ) {
		Icon(painterResource(R.drawable.baseline_add_24), "New budget")
	    }

	    LazyColumn(
		modifier = Modifier
	    ) {

		items(Cached.budgets) { budget ->
		    Card {
			Column {
			    Text(budget.name)
			}
		    }
		}

	    }
	}
    }
}
