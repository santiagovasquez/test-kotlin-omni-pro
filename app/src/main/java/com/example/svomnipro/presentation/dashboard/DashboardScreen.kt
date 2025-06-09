package com.example.svomnipro.presentation.dashboard

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.svomnipro.presentation.ui.theme.Primary
import com.example.svomnipro.data.remote.dto.CharacterDTO
import com.example.svomnipro.presentation.components.snackbar.CustomSnackBar
import com.example.svomnipro.presentation.navigation.Routes
import com.example.svomnipro.presentation.dashboard.DashboardEvent.OnClearSnack
import com.example.svomnipro.presentation.ui.theme.AquaBlueTransparent
import com.example.svomnipro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: DashboardViewModel,
    context: Context,
    activity: Activity?,
    onCloseApp: () -> Unit,
    onSelectCharacter: (Int) -> Unit,
    navController: NavHostController,
    onEvent: (DashboardEvent) -> Unit,
) {
    val state = viewModel.state.value
    val hotState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()


    val isAtBottom = remember {
        derivedStateOf {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisibleItemIndex >= totalItems - 1 && totalItems > 0
        }
    }

    if (!state.loading) {
        LaunchedEffect(isAtBottom) {
            snapshotFlow { isAtBottom.value }
                .collect { atBottom ->
                    if (atBottom) {
                        viewModel.loadNextPage()
                    }
                }
        }
    }




    viewModel.navController.value = navController

    BackHandler(enabled = true) {
        onCloseApp()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = Routes.CharacterDetail.title,
                        color = Primary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onCloseApp()
                        },
                        enabled = true
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
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

            if (state.loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f))
                        .pointerInput(Unit) { }
                        .zIndex(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = Primary)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(R.string.loading_message),
                            fontSize = 16.sp,
                            color = Primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.characters) { character ->
                        PreviewCardCharacter(character, onSelectCharacter)
                    }
                }
            }
        }
    )

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
}

@Composable
fun PreviewCardCharacter(character: CharacterDTO, onSelectCharacter: (Int) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelectCharacter(character.id)
            },
    ) {
        Row(
            modifier = Modifier
                .background(AquaBlueTransparent)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
                Text(
                    text = "Status: ${character.status}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ver detalle",
                tint = Primary,
                modifier = Modifier.size(20.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterCardItem() {
    val exampleCharacter = CharacterDTO(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        gender = "Male",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    )
    PreviewCardCharacter(character = exampleCharacter, onSelectCharacter = {  })
}



