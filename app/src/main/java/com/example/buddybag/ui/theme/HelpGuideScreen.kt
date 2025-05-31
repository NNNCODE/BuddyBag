package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buddybag.data.HelpItem
import com.example.buddybag.data.Language

@Composable
fun HelpGuideScreen(helpItems: List<HelpItem>, lang:Language) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("🆘 Local Life Guide", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(helpItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(item.getTitle(lang), style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(item.getDescription(lang),style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
