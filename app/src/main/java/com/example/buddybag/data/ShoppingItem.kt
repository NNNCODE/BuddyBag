package com.example.buddybag.data

// Model for a shopping list item
data class ShoppingItem(
    val id: String,
    val name: String,
    var isChecked: Boolean
)
