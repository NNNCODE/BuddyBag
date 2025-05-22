package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToPhrasebook: () -> Unit,
    onNavigateToChecklist: () -> Unit,
    onNavigateToShoppingList: () -> Unit,
    onNavigateToHelp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🎒 BuddyBag",
            style = MaterialTheme.typography.headlineLarge
        )

        // Navigation buttons for each functional module
        Button(
            onClick = onNavigateToPhrasebook,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📖 Phrasebook")
        }

        Button(onClick = onNavigateToChecklist, modifier = Modifier.fillMaxWidth()) {
            Text("📋 Registration Checklist")
        }

        Button(onClick = onNavigateToShoppingList, modifier = Modifier.fillMaxWidth()) {
            Text("🛒 Shopping Assistant")
        }

        Button(onClick = onNavigateToHelp, modifier = Modifier.fillMaxWidth()) {
            Text("🆘 Local Life Guide")
        }

        Text(
            text = "✅ HomeScreen rendered",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
