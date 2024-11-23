package io.github.vkindl.notes.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.vkindl.notes.core.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun observeAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id = :id")
    fun observeNoteById(id: Int): Flow<NoteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteNoteById(id: Int)
}
