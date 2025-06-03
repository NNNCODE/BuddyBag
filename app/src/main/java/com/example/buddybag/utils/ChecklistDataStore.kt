package com.example.buddybag.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// ✅ Create DataStore extension property
val Context.checklistDataStore by preferencesDataStore(name = "checklist_prefs")

class ChecklistDataStore(private val context: Context) {

    // ✅ Used to persist checklist item checked state
    private fun itemKey(itemId: String) = booleanPreferencesKey("item_checked_$itemId")

    // Save item checked state
    suspend fun saveItemChecked(itemId: String, isChecked: Boolean) {
        context.checklistDataStore.edit { prefs ->
            prefs[itemKey(itemId)] = isChecked
        }
    }

    // Read item checked state
    fun isItemChecked(itemId: String): Flow<Boolean> {
        return context.checklistDataStore.data.map { prefs ->
            prefs[itemKey(itemId)] ?: false
        }
    }

    // ✅ Used to persist selected language
    companion object {
        private val LANGUAGE_KEY = stringPreferencesKey("selected_language")
    }

    // Save current language (e.g., "EN", "FR", "ZH")
    suspend fun saveLanguage(languageCode: String) {
        context.checklistDataStore.edit { prefs ->
            prefs[LANGUAGE_KEY] = languageCode
        }
    }

    // Read saved language (nullable)
    val currentLanguage: Flow<String?> = context.checklistDataStore.data.map { prefs ->
        prefs[LANGUAGE_KEY]
    }
}
