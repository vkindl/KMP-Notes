package io.github.vkindl.notes.core.data.source

import io.github.vkindl.notes.core.data.database.NoteDao
import io.github.vkindl.notes.core.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteLocalDataSourceImpl(
    private val noteDao: NoteDao
) : NoteLocalDataSource {

    override fun observeAllNotes(): Flow<List<NoteEntity>> {
        return noteDao.observeAllNotes()
    }

    override fun observeNoteById(id: Int): Flow<NoteEntity?> {
        return noteDao.observeNoteById(id)
    }

    override suspend fun insertNote(note: NoteEntity) {
        return noteDao.insertNote(note)
    }

    override suspend fun deleteNoteById(id: Int) {
        return noteDao.deleteNoteById(id)
    }
}
