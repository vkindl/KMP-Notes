package io.github.vkindl.notes.core.domain

import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun observeAllNotes(): Flow<List<Note>>

    fun observeNoteById(id: Int): Flow<Note?>

    suspend fun insertNote(note: Note)

    suspend fun deleteNoteById(id: Int)
}
