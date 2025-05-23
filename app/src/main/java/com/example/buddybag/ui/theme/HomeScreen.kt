package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buddybag.data.Language


@Composable
fun LanguageSelector(current: Language, onChange: (Language) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Button(onClick = { onChange(Language.EN) }) {
            Text("🇬🇧 EN")
        }
        Button(onClick = { onChange(Language.FR) }) {
            Text("🇫🇷 FR")
        }
        Button(onClick = { onChange(Language.ZH) }) {
            Text("🇨🇳 中文")
        }
    }
}

@Composable
fun HomeScreen(
    onNavigateToPhrasebook: () -> Unit,
    onNavigateToChecklist: () -> Unit,
    onNavigateToShoppingList: () -> Unit,
    onNavigateToHelp: () -> Unit,
    currentLanguage: Language,
    onLanguageChange: (Language) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔤 Language switch buttons
        LanguageSelector(current = currentLanguage, onChange = onLanguageChange)


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
