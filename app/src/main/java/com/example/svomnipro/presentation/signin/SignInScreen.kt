package com.example.svomnipro.presentation.signin

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.svomnipro.presentation.ui.theme.Primary
import com.example.svomnipro.presentation.ui.theme.PrimaryLight
import androidx.compose.foundation.lazy.items


@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    context: Context,
    activity: Activity?,
    onCloseApp: () -> Unit,
    navController: NavHostController,
) {
    val state = viewModel.state.value

    if (state.showToast) {
        LaunchedEffect(true) {
            state.messageToast?.let { message ->
                Toast.makeText(context, context.getString(message), Toast.LENGTH_LONG).show()
                viewModel.clearToast()
            }
        }
    }

    viewModel.navController.value = navController

    BackHandler(enabled = true) {
        onCloseApp()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryLight)
    ) {
        // Header
        Header(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopEnd),
            onCloseApp = { onCloseApp() }
        )

        // Lista quemada
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf("Producto A", "Producto B", "Producto C", "Producto D")) { item ->
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = item,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Header(modifier: Modifier, onCloseApp: () -> Unit) {
    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
        IconButton(onClick = onCloseApp, modifier = Modifier.wrapContentSize()) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close",
                tint = Color.White
            )
        }
    }
}

