package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buddybag.data.Phrase

@Composable
fun PhrasebookScreen(phrases: List<Phrase>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("📖 Phrasebook", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(phrases) { phrase ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🇨🇳 ${phrase.chinese}")
                        Text("🇫🇷 ${phrase.french}")
                        Text("🇬🇧 ${phrase.english}")
                    }
                }
            }
        }
    }
}
