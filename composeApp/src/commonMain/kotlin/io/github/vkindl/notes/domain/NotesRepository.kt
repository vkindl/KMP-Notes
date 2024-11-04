package io.github.vkindl.notes.domain

import io.github.vkindl.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun observeAllNotes(): Flow<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun deleteNoteById(id: Int)
}
