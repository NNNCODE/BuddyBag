package com.example.buddybag.data
import com.example.buddybag.data.Language

// Data model representing a phrase in multiple languages
data class Phrase(
    val category: String,
    val zh: String,
    val fr: String,
    val en: String
){
    fun getText(lang: Language): String {
        return when (lang) {
            Language.ZH -> zh
            Language.FR -> fr
            Language.EN -> en
        }
    }
}
