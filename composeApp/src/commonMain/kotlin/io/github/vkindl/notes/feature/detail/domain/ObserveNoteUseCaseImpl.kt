package io.github.vkindl.notes.feature.detail.domain

import io.github.vkindl.notes.core.domain.NoteRepository
import io.github.vkindl.notes.core.domain.Note
import kotlinx.coroutines.flow.Flow

class ObserveNoteUseCaseImpl(
    private val noteRepository: NoteRepository
): ObserveNoteUseCase {

    override operator fun invoke(id: Int): Flow<Note?> {
        return noteRepository.observeNoteById(id)
    }
}

interface ObserveNoteUseCase {
    operator fun invoke(id: Int): Flow<Note?>
}
