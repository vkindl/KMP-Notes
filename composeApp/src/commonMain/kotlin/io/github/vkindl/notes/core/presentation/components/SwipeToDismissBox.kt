package io.github.vkindl.notes.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import composemultiplatformnotes.composeapp.generated.resources.Res
import composemultiplatformnotes.composeapp.generated.resources.common_content_description_delete
import io.github.vkindl.notes.core.presentation.theme.dimens
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotesSwipeToDismissBox(
    action: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                action()
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(MaterialTheme.dimens.medium))
                    .background(MaterialTheme.colorScheme.error)
            ) {
                DeleteIcon(modifier = Modifier.align(Alignment.CenterEnd))
            }
        }
    ) {
        content()
    }
}

@Composable
private fun DeleteIcon(
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier.padding(MaterialTheme.dimens.medium),
        imageVector = Icons.Default.Delete,
        tint = MaterialTheme.colorScheme.onError,
        contentDescription = stringResource(Res.string.common_content_description_delete)
    )
}
