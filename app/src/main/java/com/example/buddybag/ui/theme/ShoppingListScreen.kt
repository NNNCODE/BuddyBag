package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buddybag.data.ShoppingItem

@Composable
fun ShoppingListScreen(initialItems: List<ShoppingItem>) {
    var items by remember { mutableStateOf(initialItems) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("🛒 Shopping Assistant", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(items) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item.name)
                        Checkbox(
                            checked = item.isChecked,
                            onCheckedChange = { isChecked ->
                                items = items.map {
                                    if (it == item) it.copy(isChecked = isChecked) else it
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
