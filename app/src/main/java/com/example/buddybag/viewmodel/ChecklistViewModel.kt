package com.example.buddybag.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.buddybag.data.ChecklistItem
import com.example.buddybag.utils.ChecklistDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChecklistViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val dataStore = ChecklistDataStore(context)

    private val initialItems = listOf(
        ChecklistItem("caf_registration", "CAF Registration", "Housing subsidy", false),
        ChecklistItem("ameli_account", "Ameli (Health Insurance)", "Create an Ameli account", false),
        ChecklistItem("open_bank_account", "Open a bank account", "LCL, SG, BNP, Revolut etc.", false),
        ChecklistItem("student_card", "Get student card", "From your school", false),
        ChecklistItem("ofii_registration", "OFII registration", "For non-EU students", false)
    )

    private val _checklist = MutableStateFlow(initialItems)
    val checklist: StateFlow<List<ChecklistItem>> = _checklist

    init {
        loadSavedStates()
    }

    private fun loadSavedStates() {
        viewModelScope.launch {
            initialItems.forEach { item ->
                dataStore.isItemChecked(item.id).collect { checked ->
                    _checklist.update { list ->
                        list.map {
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
            _checklist.update { list ->
                list.map {
                    if (it.id == itemId) it.copy(isChecked = isChecked) else it
                }
            }
        }
    }

    fun addCustomItem(item: ChecklistItem) {
        _checklist.update { current ->
            if (current.any { it.id == item.id }) current else current + item
        }
    }
}
