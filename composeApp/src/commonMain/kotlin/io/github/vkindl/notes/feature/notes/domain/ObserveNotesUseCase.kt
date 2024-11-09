package io.github.vkindl.notes.feature.notes.domain

import io.github.vkindl.notes.core.domain.NotesRepository
import io.github.vkindl.notes.core.domain.Note
import kotlinx.coroutines.flow.Flow

class ObserveNotesUseCase(
    private val notesRepository: NotesRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return notesRepository.observeAllNotes()
    }
}
