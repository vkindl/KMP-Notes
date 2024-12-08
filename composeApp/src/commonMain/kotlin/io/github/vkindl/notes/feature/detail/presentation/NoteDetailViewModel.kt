package io.github.vkindl.notes.feature.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vkindl.notes.core.domain.Note
import io.github.vkindl.notes.core.domain.SelectedNoteRepository
import io.github.vkindl.notes.feature.detail.domain.ObserveNoteUseCase
import io.github.vkindl.notes.feature.detail.domain.SaveNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    private val observeNoteUseCase: ObserveNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val selectedNoteRepository: SelectedNoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NoteDetailUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            selectedNoteRepository.getNoteId()?.let { id ->
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
            id = selectedNoteRepository.getNoteId() ?: _state.value.id,
            title = _state.value.title,
            description = _state.value.description
        )
    }

    data class NoteDetailUiState(
        val id: Int = 0,
        val title: String = "",
        val description: String? = null
    )
}
