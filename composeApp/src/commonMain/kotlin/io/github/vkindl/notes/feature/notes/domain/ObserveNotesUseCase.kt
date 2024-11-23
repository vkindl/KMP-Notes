package io.github.vkindl.notes.feature.notes.domain

import io.github.vkindl.notes.core.domain.NoteRepository
import io.github.vkindl.notes.core.domain.Note
import kotlinx.coroutines.flow.Flow

class ObserveNotesUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.observeAllNotes()
    }
}
