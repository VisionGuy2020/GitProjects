package com.trivianighthub.leaguetrivia.ui.league

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trivianighthub.leaguetrivia.util.DataResult

@Composable
fun LeagueChoiceScreen(
    modifier: Modifier = Modifier,
    viewModel: LeagueViewModel = viewModel(),
    onNavigateToCreateLeague: () -> Unit
) {
    LaunchedEffect(Unit) {
        when (viewModel.leagues.value) {
            is DataResult.Success -> {
                // Leagues loaded successfully
            }
            else -> {}
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to League Trivia",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = onNavigateToCreateLeague,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Create New League")
        }

        Button(
            onClick = { /* TODO: Join League */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Join League")
        }

        if (viewModel.leagues.value is DataResult.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp)
            )
        }

        if (viewModel.leagues.value is DataResult.Error) {
            Text(
                text = (viewModel.leagues.value as DataResult.Error).message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
} 