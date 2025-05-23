package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buddybag.data.Language
import com.example.buddybag.data.Phrase

/**
 * PhrasebookScreen displays a scrollable list of translated phrases
 * in the currently selected language.
 *
 * @param phrases List of Phrase objects containing all translations
 * @param lang The language currently selected (EN, FR, ZH)
 */

@Composable
fun PhrasebookScreen(
    phrases: List<Phrase>,
    lang: Language
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            "📖 Phrasebook",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        //It is used to render the content of each phrase card in Phrasebook:
        //
        //Display translated text (according to the current language)
        //
        //displaying categorized tags (e.g. “Restaurant”, “Transportation”)
        LazyColumn {
            items(phrases) { phrase ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "🔤 ${phrase.getText(lang)}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "🏷️  Category: ${phrase.category}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
