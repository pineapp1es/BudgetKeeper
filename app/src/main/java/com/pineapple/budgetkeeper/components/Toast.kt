package com.pineapple.budgetkeeper.components

import com.pineapple.budgetkeeper.R

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape

import kotlinx.coroutines.delay

@Composable
fun Toast(
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    xoffset: Dp = 0.dp,
    yoffset: Dp = 0.dp,
    duration: Long = 3000,
    onDismiss: () -> Unit,
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
	delay(duration)
	if (isVisible) {
	    isVisible = false
	    onDismiss()
	}
    }

    AnimatedVisibility(
	visible = isVisible,
    ) {
	Box(
	    modifier = Modifier
		.offset(x = xoffset, y = yoffset)
                .fillMaxWidth()
                .fillMaxHeight()
		.fillMaxSize()
	   ,
	) {
	    Surface(
                color = Color(0xAAAAEE00),
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 8.dp
            ) {
		Column {
		    Row {
			IconButton(
			    onClick = { isVisible = false; onDismiss() }
			){
			    Icon(painterResource(R.drawable.baseline_close_24), "Dismiss Toast");
			}
			title()
		    }
		    content()
		}
	    }
	}
    }
}
