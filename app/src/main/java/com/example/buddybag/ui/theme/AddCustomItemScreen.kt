package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun AddCustomItemScreen(
    navController: NavController,
    onAddItem: (String, String) -> Unit // id, text
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("➕ Add Custom Item", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (text.isNotBlank()) {
                    val id = UUID.randomUUID().toString()
                    onAddItem(id, text)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add")
        }
    }
}
