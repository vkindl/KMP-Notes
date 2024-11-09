package io.github.vkindl.notes.feature.detail.domain

import io.github.vkindl.notes.core.domain.NotesRepository
import io.github.vkindl.notes.core.domain.Note

class SaveNoteUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        notesRepository.insertNote(note)
    }
}
