package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buddybag.viewmodel.ShoppingViewModel

@Composable
fun ShoppingListScreen(viewModel: ShoppingViewModel = viewModel()) {
    val items by viewModel.items.collectAsState()

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
                                viewModel.onItemCheckedChanged(item.id, isChecked)
                            }
                        )
                    }
                }
            }
        }
    }
}
