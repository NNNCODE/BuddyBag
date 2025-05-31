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

/**
 * Returns translated label text based on key and language.
 */
fun getLabel(key: String, lang: Language): String {
    return when (key) {
        "phrasebook" -> when (lang) {
            Language.EN -> "Phrasebook"
            Language.FR -> "Phrases utiles"
            Language.ZH -> "常用短语"
        }
        "checklist" -> when (lang) {
            Language.EN -> "Registration Checklist"
            Language.FR -> "Liste d'inscription"
            Language.ZH -> "注册清单"
        }
        "shopping" -> when (lang) {
            Language.EN -> "Shopping Assistant"
            Language.FR -> "Assistant de courses"
            Language.ZH -> "购物助手"
        }
        "help" -> when (lang) {
            Language.EN -> "Local Life Guide"
            Language.FR -> "Guide local"
            Language.ZH -> "生活指南"
        }
        else -> key
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
            Text("📖 " + getLabel("phrasebook", currentLanguage))
        }

        Button(onClick = onNavigateToChecklist, modifier = Modifier.fillMaxWidth()) {
            Text("📋 " + getLabel("checklist", currentLanguage))
        }

        Button(onClick = onNavigateToShoppingList, modifier = Modifier.fillMaxWidth()) {
            Text("🛒 " + getLabel("shopping", currentLanguage))
        }

        Button(onClick = onNavigateToHelp, modifier = Modifier.fillMaxWidth()) {
            Text("🆘 " + getLabel("help", currentLanguage))
        }

        Text(
            text = "✅ HomeScreen rendered",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
