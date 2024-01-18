package com.dastanapps.marketstrategy.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberTextField(
    text: MutableState<Int>,
    placeholderText: String = "",
    textStyle: TextStyle,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize
) {

    BasicTextField(
        value = text.value.toString(),
        onValueChange = {
            text.value = it.toInt()
        },
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.background,
                MaterialTheme.shapes.small,
            ),
        textStyle = textStyle.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = fontSize
        ),
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        keyboardOptions = keyboardOptions,
        decorationBox = @Composable { innerTextField ->
            val colors = OutlinedTextFieldDefaults.colors()
            val shape = OutlinedTextFieldDefaults.shape
            val interactionSource: MutableInteractionSource =
                remember { MutableInteractionSource() }
            val enabled = true
            val isError = false
            OutlinedTextFieldDefaults.DecorationBox(
                value = text.value.toString(),
                innerTextField = innerTextField,
                singleLine = true,
                enabled = enabled,
                isError = isError,
                placeholder = {
                    Text(text = placeholderText)
                },
                interactionSource = interactionSource,
                colors = colors,
                visualTransformation = VisualTransformation.None,
                contentPadding = PaddingValues(0.dp),
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = shape
                    )
                }
            )
        }
    )
}

