package io.github.vkindl.notes.domain.usecase

import io.github.vkindl.notes.domain.NotesRepository

class DeleteNoteUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(id: Int) {
        notesRepository.deleteNoteById(id)
    }
}
