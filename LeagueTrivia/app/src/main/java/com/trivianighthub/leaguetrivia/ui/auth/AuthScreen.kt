package com.trivianighthub.leaguetrivia.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AuthScreen(
    onAuthSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    val authState by viewModel.authState.collectAsState()
    
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onAuthSuccess()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Email sign in fields
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { viewModel.signInWithEmail(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign in with Email")
        }
        
        TextButton(
            onClick = { viewModel.createAccountWithEmail(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Account")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Social sign in buttons
        Button(
            onClick = { viewModel.signInWithGoogle() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign in with Google")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
            onClick = { viewModel.signInWithFacebook() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign in with Facebook")
        }

        // Show error if any
        if (authState is AuthState.Error) {
            Text(
                text = (authState as AuthState.Error).message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        
        // Show loading indicator
        if (authState is AuthState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
} 