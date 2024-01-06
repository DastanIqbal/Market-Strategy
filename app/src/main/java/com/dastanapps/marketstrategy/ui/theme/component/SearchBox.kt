package com.dastanapps.marketstrategy.ui.theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * Author: Iqbal Ahmed
 * Date: 07/01/2024
 * Description: Brief description of the file or purpose.
 */


data class SearchBoxState(val value: MutableState<String>, val valueChange:(String)->Unit)

@Composable
fun SearchBox(
    state: SearchBoxState
) {
    TextField(
        value = state.value.value,
        leadingIcon = {
            Image(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        onValueChange = {
            state.value.value = it
            state.valueChange(it)
        },
        placeholder = {
            Text(text = "Search")
        },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBoxPreview() {
    SearchBox(SearchBoxState(mutableStateOf(""),{}))
}