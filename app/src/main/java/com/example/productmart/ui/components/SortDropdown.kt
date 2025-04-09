package com.example.productmart.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SortDropdown(
    selectedSort: String,
    onSortChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val sortOptions = listOf("title_asc", "price_asc", "price_desc")
    val sortLabels = mapOf(
        "title_asc" to "Title (A-Z)",
        "price_asc" to "Price (Low to High)",
        "price_desc" to "Price (High to Low)"
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)) {

        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(sortLabels[selectedSort] ?: "Sort By")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            sortOptions.forEach { sort ->
                DropdownMenuItem(
                    text = { Text(sortLabels[sort] ?: sort) },
                    onClick = {
                        expanded = false
                        onSortChange(sort)
                    }
                )
            }
        }
    }
}
