package com.example.buddybag.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.buddybag.data.ChecklistItem


//	Page: Checkable to-do list
@Composable
fun ChecklistScreen(initialItems: List<ChecklistItem>) {
    var checklist by remember { mutableStateOf(initialItems) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("📋 Registration Checklist", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(checklist) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.title)
                            Text(text = item.description, style = MaterialTheme.typography.bodySmall)
                        }
                        Checkbox(
                            checked = item.isChecked,
                            onCheckedChange = {
                                /*
                                * ❗ 为什么不行？
                                你只是改了 item.isChecked

                                然后虽然 checklist = checklist.toList()，但里面的对象还是旧引用

                                Compose 的 diff 算法不会识别对象内部属性变了（因为引用没变）
                                *
                                * */
                                //item.isChecked = it
                                //checklist = checklist.toList() // Force recomposition
                                    isChecked ->
                                checklist = checklist.map { i ->
                                    if (i == item) i.copy(isChecked = isChecked) else i

                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
