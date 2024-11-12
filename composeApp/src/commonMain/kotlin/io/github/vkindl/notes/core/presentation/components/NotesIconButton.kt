package io.github.vkindl.notes.core.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.vkindl.notes.core.presentation.theme.NotesTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NotesIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

@Composable
@Preview
private fun NotesIconButtonPreview() {
    NotesTheme {
        NotesIconButton(
            imageVector = Icons.Default.Delete,
            contentDescription = "",
            onClick = {}
        )
    }
}
