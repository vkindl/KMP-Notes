package io.github.vkindl.notes.feature.detail.domain

import io.github.vkindl.notes.core.domain.NoteRepository
import io.github.vkindl.notes.core.domain.Note

class SaveNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.insertNote(note)
    }
}
