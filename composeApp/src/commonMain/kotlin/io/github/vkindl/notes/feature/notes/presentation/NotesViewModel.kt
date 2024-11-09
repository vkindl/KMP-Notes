package io.github.vkindl.notes.feature.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vkindl.notes.core.domain.Note
import io.github.vkindl.notes.feature.notes.domain.DeleteNoteUseCase
import io.github.vkindl.notes.feature.notes.domain.ObserveNotesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    observeNotesUseCase: ObserveNotesUseCase
) : ViewModel() {

    val state = observeNotesUseCase().map { notes ->
        if (notes.isEmpty()) NotesUiState.EMPTY else NotesUiState.NOTES(notes)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        NotesUiState.EMPTY
    )

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            deleteNoteUseCase(id)
        }
    }

    sealed interface NotesUiState {
        data object EMPTY : NotesUiState
        data class NOTES(val notes: List<Note>) : NotesUiState
    }
}
