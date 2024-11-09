package io.github.vkindl.notes.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import io.github.vkindl.notes.core.domain.Note
import io.github.vkindl.notes.feature.detail.domain.ObserveNoteUseCase
import io.github.vkindl.notes.feature.detail.domain.SaveNoteUseCase
import io.github.vkindl.notes.navigation.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val observeNoteUseCase: ObserveNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
) : ViewModel() {

    private val articleId = savedStateHandle.toRoute<Destination.Detail>().id

    private val _state = MutableStateFlow(NoteDetailUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            articleId?.let { id ->
                observeNoteUseCase(id).collect { note ->
                    _state.update {
                        it.copy(
                            title = note?.title.orEmpty(),
                            description = note?.description
                        )
                    }
                }
            }
        }
    }

    fun onTitleChange(title: String) {
        _state.update { it.copy(title = title) }
    }

    fun onDescriptionChange(description: String) {
        _state.update { it.copy(description = description) }
    }

    fun saveNote() {
        viewModelScope.launch {
            saveNoteUseCase(createNote())
        }
    }

    private fun createNote(): Note {
        return Note(
            id = articleId ?: _state.value.id,
            title = _state.value.title.takeIf { it.isNotBlank() } ?: "Untitled",
            description = _state.value.description
        )
    }

    data class NoteDetailUiState(
        val id: Int = 0,
        val title: String = "",
        val description: String? = null
    )
}
