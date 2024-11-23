package io.github.vkindl.notes.feature.detail.domain

import io.github.vkindl.notes.core.domain.NoteRepository
import io.github.vkindl.notes.core.domain.Note
import kotlinx.coroutines.flow.Flow

class ObserveNoteUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(id: Int): Flow<Note?> {
        return repository.observeNoteById(id)
    }
}
