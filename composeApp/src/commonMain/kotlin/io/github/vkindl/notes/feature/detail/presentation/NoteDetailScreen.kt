package io.github.vkindl.notes.feature.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import composemultiplatformnotes.composeapp.generated.resources.Res
import composemultiplatformnotes.composeapp.generated.resources.common_content_description_back
import composemultiplatformnotes.composeapp.generated.resources.common_content_description_save
import composemultiplatformnotes.composeapp.generated.resources.note_detail_screen_note_description_placeholder
import composemultiplatformnotes.composeapp.generated.resources.note_detail_screen_note_title_placeholder
import io.github.vkindl.notes.core.presentation.components.NotesIconButton
import io.github.vkindl.notes.core.presentation.components.NotesTextField
import io.github.vkindl.notes.core.presentation.components.NotesTopAppBar
import io.github.vkindl.notes.core.presentation.theme.NotesTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NoteDetailScreen(
    viewModel: NoteDetailViewModel = koinViewModel(),
    navBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Content(
        state = state,
        onBack = navBack,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onSave = viewModel::saveNote
    )
}

@Composable
private fun Content(
    state: NoteDetailViewModel.NoteDetailUiState,
    onBack: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = {
            NotesTopAppBar(
                title = {},
                navigationIcon = {
                    NotesIconButton(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(
                            Res.string.common_content_description_back
                        ),
                        onClick = onBack
                    )
                },
                actions = {
                    NotesIconButton(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(
                            Res.string.common_content_description_save
                        ),
                        onClick = {
                            onSave()
                            onBack()
                        }
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            NotesTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.title,
                singleLine = true,
                textStyle = MaterialTheme.typography.titleLarge,
                onValueChange = onTitleChange,
                placeholder = stringResource(Res.string.note_detail_screen_note_title_placeholder)
            )
            NotesTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.description.orEmpty(),
                onValueChange = onDescriptionChange,
                placeholder = stringResource(
                    Res.string.note_detail_screen_note_description_placeholder
                )
            )
        }
    }
}

@Composable
@Preview
private fun NoteDetailScreenPreview() {
    NotesTheme {
        Content(
            state = NoteDetailViewModel.NoteDetailUiState(
                id = 1,
                title = "Title",
                description = "Description",
            ),
            onBack = {},
            onTitleChange = {},
            onDescriptionChange = {},
            onSave = {}
        )
    }
}
