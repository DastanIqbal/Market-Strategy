package com.dastanapps.marketstrategy.ui.theme.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * Author: Iqbal Ahmed
 * Date: 07/01/2024
 * Description: Brief description of the file or purpose.
 */

@Composable
fun Dropdown(
    items: List<String>,
    selectedItem: String,
    onSelectedItemChanged: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

   Box(
        modifier = Modifier.fillMaxWidth()
            .clickable { expanded.value = !expanded.value }
    ) {
        Text(
            text = selectedItem.ifEmpty { "Choose Symbol" },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onSelectedItemChanged(item)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownPreview() {
    val items = listOf("Item 1", "Item 2", "Item 3")
    val selectedItem = remember { mutableStateOf("Item 1") }

    Column {
        Dropdown(
            items = items,
            selectedItem = selectedItem.value,
            onSelectedItemChanged = { selectedItem.value = it }
        )

        Text(text = "Selected item: ${selectedItem.value}")
    }
}