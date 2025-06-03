package com.example.buddybag.data

// Data model for a checklist item (e.g., "Register for CAF")
data class ChecklistItem(
    val id: String,           // ✅ Unique ID for persistent identification
    val title: String,        // Task title
    val description: String,  // Optional description
    var isChecked: Boolean    // Completion status
)
