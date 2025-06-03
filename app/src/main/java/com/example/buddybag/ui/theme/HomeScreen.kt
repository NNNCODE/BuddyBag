package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buddybag.data.Language
import com.example.buddybag.data.ChecklistItem

@Composable
fun LanguageSelector(current: Language, onChange: (Language) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Button(onClick = { onChange(Language.EN) }) {
            Text("\uD83C\uDDEC\uD83C\uDDE7 EN")
        }
        Button(onClick = { onChange(Language.FR) }) {
            Text("\uD83C\uDDEB\uD83C\uDDF7 FR")
        }
        Button(onClick = { onChange(Language.ZH) }) {
            Text("\uD83C\uDDE8\uD83C\uDDF3 中文")
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
        "profile" -> when (lang) {
            Language.EN -> "My Profile"
            Language.FR -> "Mon profil"
            Language.ZH -> "我的资料"
        }
        "add" -> when (lang) {
            Language.EN -> "Add Custom Item"
            Language.FR -> "Ajouter une entrée"
            Language.ZH -> "添加自定义项"
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
    onNavigateToProfile: () -> Unit,
    onNavigateToAddCustomItem: () -> Unit,
    currentLanguage: Language,
    onLanguageChange: (Language) -> Unit,
    onCustomItemAdded: (ChecklistItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LanguageSelector(current = currentLanguage, onChange = onLanguageChange)

        Text(
            text = "\uD83C\uDF92 BuddyBag",
            style = MaterialTheme.typography.headlineLarge
        )

        Button(onClick = onNavigateToPhrasebook, modifier = Modifier.fillMaxWidth()) {
            Text("\uD83D\uDCD6 " + getLabel("phrasebook", currentLanguage))
        }

        Button(onClick = onNavigateToChecklist, modifier = Modifier.fillMaxWidth()) {
            Text("\uD83D\uDCCB " + getLabel("checklist", currentLanguage))
        }

        Button(onClick = onNavigateToShoppingList, modifier = Modifier.fillMaxWidth()) {
            Text("\uD83D\uDED2 " + getLabel("shopping", currentLanguage))
        }

        Button(onClick = onNavigateToHelp, modifier = Modifier.fillMaxWidth()) {
            Text("\uD83D\uDEA8 " + getLabel("help", currentLanguage))
        }

        Button(onClick = onNavigateToProfile, modifier = Modifier.fillMaxWidth()) {
            Text("\uD83D\uDC64 " + getLabel("profile", currentLanguage))
        }

        Button(onClick = onNavigateToAddCustomItem, modifier = Modifier.fillMaxWidth()) {
            Text("➕ " + getLabel("add", currentLanguage))
        }

        Text(
            text = "\u2705 HomeScreen rendered",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
