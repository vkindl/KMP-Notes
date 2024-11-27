package io.github.vkindl.notes.feature.notes.domain

import io.github.vkindl.notes.core.domain.NoteRepository

class DeleteNoteUseCaseImpl(
    private val noteRepository: NoteRepository
): DeleteNoteUseCase {

    override suspend operator fun invoke(id: Int) {
        noteRepository.deleteNoteById(id)
    }
}

interface DeleteNoteUseCase {
    suspend operator fun invoke(id: Int)
}
