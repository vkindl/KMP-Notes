package io.github.vkindl.notes.core.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

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
