package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.buddybag.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val user by authViewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("👤 My Profile", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Email: ${user?.email ?: "Unknown"}")

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            authViewModel.logout()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text("Log out")
        }
    }
}
