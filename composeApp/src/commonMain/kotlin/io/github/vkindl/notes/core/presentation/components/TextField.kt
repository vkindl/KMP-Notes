package io.github.vkindl.notes.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import io.github.vkindl.notes.core.presentation.theme.NotesTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NotesTextField(
    value: String,
    singleLine: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        singleLine = singleLine,
        textStyle = textStyle,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = textStyle
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
@Preview
private fun NotesTextFieldPreview() {
    NotesTheme {
        NotesTextField(
            value = "Text",
            onValueChange = {},
            placeholder = "Text"
        )
    }
}
