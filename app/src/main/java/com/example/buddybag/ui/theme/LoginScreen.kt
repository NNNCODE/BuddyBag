package com.example.buddybag.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.buddybag.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val user by authViewModel.user.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLogin by remember { mutableStateOf(true) }

    // 自动跳转（登录成功）
    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(if (isLogin) "Login" else "Register", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isLogin) {
                    authViewModel.loginWithEmail(email, password) { success, error ->
                        if (!success) {
                            Toast.makeText(context, "Login failed: $error", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    authViewModel.registerWithEmail(email, password) { success, error ->
                        if (!success) {
                            Toast.makeText(context, "Register failed: $error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        ) {
            Text(if (isLogin) "Login" else "Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { isLogin = !isLogin }) {
            Text(if (isLogin) "Don't have an account? Register" else "Already have an account? Login")
        }
    }
}
