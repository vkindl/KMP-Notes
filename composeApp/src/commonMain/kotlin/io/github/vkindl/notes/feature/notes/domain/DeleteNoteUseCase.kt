package io.github.vkindl.notes.feature.notes.domain

import io.github.vkindl.notes.core.domain.NotesRepository

class DeleteNoteUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(id: Int) {
        notesRepository.deleteNoteById(id)
    }
}
