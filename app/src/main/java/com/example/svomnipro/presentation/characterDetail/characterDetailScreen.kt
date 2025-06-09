package com.example.svomnipro.presentation.characterDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.svomnipro.R
import com.example.svomnipro.presentation.components.snackbar.CustomSnackBar
import com.example.svomnipro.presentation.characterDetail.CharacterDetailEvent.OnClearSnack
import com.example.svomnipro.presentation.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContingencyScreen(
    navController: NavController,
    viewModel: CharacterDetailViewModel,
    onEvent: (CharacterDetailEvent) -> Unit,
) {
    val state = viewModel.state.value
    var isBackEnabled by remember { mutableStateOf(true) }
    val hotState = remember { SnackbarHostState() }

    if (state.showSnack) {
        LaunchedEffect(true) {
            if (state.showSnack) {
                try {
                    when (hotState.showSnackbar(
                        message = "show"
                    )) {
                        SnackbarResult.Dismissed -> {
                            onEvent(OnClearSnack)
                        }

                        else -> {}
                    }
                } finally {
                    onEvent(OnClearSnack)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.characterDetail?.name ?: stringResource(R.string.character),
                        color = Primary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                            isBackEnabled = false
                        },
                        enabled = isBackEnabled
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = Primary
                        )
                    }
                })
        },
        snackbarHost = {
            state.typeSnackBar?.let { type ->
                SnackbarHost(
                    hostState = hotState,
                ) {
                    CustomSnackBar(type = type)
                }
            }
        },
        content = { paddingValues ->
            Surface(
                contentColor = Primary,
                modifier = Modifier.padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(Color.White)
                ) {
                    if (state.loading) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(50.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator(color = Primary)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = stringResource(R.string.loading_message),
                                    fontSize = 16.sp,
                                    color = Primary,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    } else {
                        state.characterDetail?.let {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = it.image,
                                        contentDescription = it.name,
                                        modifier = Modifier
                                            .size(170.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = it.name,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )

                                Spacer(modifier = Modifier.height(16.dp))
                                val infoPairs = listOf(
                                    "Status" to it.status,
                                    "Species" to it.species,
                                    "Type" to (it.type.ifEmpty { "N/A" }),
                                    "Gender" to it.gender,
                                    "Origin" to it.origin.name,
                                    "Location" to it.location.name,
                                    "Created" to it.created.substringBefore("T"),
                                    "Episodes" to it.episode.size.toString(),
                                    "URL" to it.url
                                )

                                infoPairs.forEach { (label, value) ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(text = label, fontWeight = FontWeight.Medium, color = Color.Gray)
                                        Text(
                                            text = value,
                                            fontWeight = FontWeight.Normal,
                                            color = Color.Black,
                                            textAlign = TextAlign.End,
                                            modifier = Modifier.widthIn(min = 100.dp, max = 200.dp)
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    )
}

