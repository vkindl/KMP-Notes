package io.github.vkindl.notes.feature.detail.domain

import io.github.vkindl.notes.core.domain.NoteRepository
import io.github.vkindl.notes.core.domain.Note

class SaveNoteUseCaseImpl(
    private val noteRepository: NoteRepository
): SaveNoteUseCase {

    override suspend operator fun invoke(note: Note) {
        noteRepository.insertNote(note)
    }
}

interface SaveNoteUseCase {
    suspend operator fun invoke(note: Note)
}
