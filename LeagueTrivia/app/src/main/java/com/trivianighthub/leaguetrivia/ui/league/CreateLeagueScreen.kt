package com.trivianighthub.leaguetrivia.ui.league

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun CreateLeagueScreen(
    modifier: Modifier = Modifier,
    viewModel: LeagueViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    var leagueName by remember { mutableStateOf("") }
    var leagueDescription by remember { mutableStateOf("") }
    var isOpenLeague by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.createLeagueResult.collectAsStateWithLifecycle().value) {
        when (viewModel.createLeagueResult.value) {
            is DataResult.Success -> {
                onNavigateBack()
                viewModel.clearCreateLeagueResult()
            }
            else -> {}
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Create a New League",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        OutlinedTextField(
            value = leagueName,
            onValueChange = { leagueName = it },
            label = { Text("League Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            isError = leagueName.isNotEmpty() && leagueName.length < 3,
            supportingText = {
                Text("Minimum 3 characters")
            }
        )

        OutlinedTextField(
            value = leagueDescription,
            onValueChange = { leagueDescription = it },
            label = { Text("League Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            isError = leagueDescription.length > 500,
            supportingText = {
                Text("Maximum 500 characters")
            }
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        Text(
            text = "League Settings",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        OutlinedTextField(
            value = "0",
            onValueChange = { },
            label = { Text("Maximum Teams") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            enabled = false,
            supportingText = {
                Text("Default: 32 teams")
            }
        )

        OutlinedTextField(
            value = "0",
            onValueChange = { },
            label = { Text("Maximum Players per Team") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            enabled = false,
            supportingText = {
                Text("Default: 10 players")
            }
        )

        if (viewModel.createLeagueResult.value?.let { it is DataResult.Error } == true) {
            Text(
                text = (viewModel.createLeagueResult.value as DataResult.Error).message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = onNavigateBack
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {
                    viewModel.createLeague(
                        name = leagueName,
                        description = leagueDescription,
                        isOpenLeague = isOpenLeague
                    )
                },
                enabled = leagueName.length >= 3 && leagueDescription.length <= 500
            ) {
                Text("Create League")
            }
        }

        if (viewModel.createLeagueResult.value is DataResult.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(24.dp)
            )
        }
    }
} 