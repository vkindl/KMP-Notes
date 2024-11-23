package io.github.vkindl.notes.core.data.source

import io.github.vkindl.notes.core.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteLocalDataSource {

    fun observeAllNotes(): Flow<List<NoteEntity>>

    fun observeNoteById(id: Int): Flow<NoteEntity?>

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNoteById(id: Int)
}
