package io.github.vkindl.notes.feature.notes.domain

import io.github.vkindl.notes.core.domain.NoteRepository

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int) {
        noteRepository.deleteNoteById(id)
    }
}
