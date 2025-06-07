package com.example.svomnipro.presentation.components.snackbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun snackBarContent(
    message: String,
    color: Color,
    icon: ImageVector,
    onHideSnack: (() -> Unit)? = null
) {
    Snackbar(
        containerColor = color,
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onHideSnack?.let { hide ->
                        hide()
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = message,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1.8f)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.2f)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
    }
}