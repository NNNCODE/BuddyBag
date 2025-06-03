package com.example.buddybag.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.buddybag.data.ShoppingItem
import com.example.buddybag.utils.ChecklistDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShoppingViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val dataStore = ChecklistDataStore(context)

    private val initialItems = listOf(
        ShoppingItem("milk", "Milk", false),
        ShoppingItem("bread", "Bread", false),
        ShoppingItem("eggs", "Eggs", false),
        ShoppingItem("toothpaste", "Toothpaste", false),
        ShoppingItem("shampoo", "Shampoo", false)
    )

    private val _items = MutableStateFlow(initialItems)
    val items: StateFlow<List<ShoppingItem>> = _items

    init {
        loadSavedStates()
    }

    private fun loadSavedStates() {
        viewModelScope.launch {
            initialItems.forEach { item ->
                dataStore.isItemChecked(item.id).collect { checked ->
                    _items.update { current ->
                        current.map {
                            if (it.id == item.id) it.copy(isChecked = checked) else it
                        }
                    }
                }
            }
        }
    }

    fun onItemCheckedChanged(itemId: String, isChecked: Boolean) {
        viewModelScope.launch {
            dataStore.saveItemChecked(itemId, isChecked)
            _items.update { current ->
                current.map {
                    if (it.id == itemId) it.copy(isChecked = isChecked) else it
                }
            }
        }
    }
}
