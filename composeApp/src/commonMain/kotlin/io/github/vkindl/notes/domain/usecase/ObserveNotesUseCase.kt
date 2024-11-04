package io.github.vkindl.notes.domain.usecase

import io.github.vkindl.notes.domain.NotesRepository
import io.github.vkindl.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

class ObserveNotesUseCase(
    private val notesRepository: NotesRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return notesRepository.observeAllNotes()
    }
}
