package io.github.vkindl.notes.feature.notes.domain

import io.github.vkindl.notes.core.domain.NoteRepository
import io.github.vkindl.notes.core.domain.Note
import kotlinx.coroutines.flow.Flow

class ObserveNotesUseCaseImpl(
    private val noteRepository: NoteRepository
) : ObserveNotesUseCase {

    override operator fun invoke(): Flow<List<Note>> {
        return noteRepository.observeAllNotes()
    }
}

interface ObserveNotesUseCase {
    operator fun invoke(): Flow<List<Note>>
}
