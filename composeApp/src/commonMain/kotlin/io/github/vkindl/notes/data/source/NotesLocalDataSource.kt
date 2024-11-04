package io.github.vkindl.notes.data.source

import io.github.vkindl.notes.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {

    fun observeAllNotes(): Flow<List<NoteEntity>>

    fun observeNoteById(id: Int): Flow<NoteEntity?>

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNoteById(id: Int)
}
