package io.github.vkindl.notes.domain.usecase

import io.github.vkindl.notes.domain.NotesRepository
import io.github.vkindl.notes.domain.model.Note

class AddNoteUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        notesRepository.insertNote(note)
    }
}
