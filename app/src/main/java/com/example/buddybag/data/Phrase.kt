package com.example.buddybag.data

// Data model representing a phrase in multiple languages
data class Phrase(
    val category: String,      // Phrase category (e.g., Restaurant, Hospital, etc.)
    val chinese: String,       // Phrase in Chinese
    val french: String,        // Phrase in French
    val english: String        // Phrase in English
)
