package com.example.buddybag.data

// Data model for help information items
data class HelpItem(
    val title_en: String,
    val desc_en: String,
    val title_fr: String,
    val desc_fr: String,
    val title_zh: String,
    val desc_zh: String
) {
    fun getTitle(lang: Language): String {
        return when (lang) {
            Language.EN -> title_en
            Language.FR -> title_fr
            Language.ZH -> title_zh
        }
    }

    fun getDescription(lang: Language): String {
        return when (lang) {
            Language.EN -> desc_en
            Language.FR -> desc_fr
            Language.ZH -> desc_zh
        }
    }
}
