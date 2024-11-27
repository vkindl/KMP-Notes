package io.github.vkindl.notes.core.data.source

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verifySuspend
import io.github.vkindl.notes.core.data.database.NoteDao
import io.github.vkindl.notes.core.data.model.NoteEntity
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class NoteLocalDataSourceImplTest {

    private lateinit var noteDao: NoteDao
    private lateinit var sut: NoteLocalDataSourceImpl

    @BeforeTest
    fun setUp() {
        noteDao = mock<NoteDao>()

        sut = NoteLocalDataSourceImpl(
            noteDao = noteDao
        )
    }

    @Test
    fun `should observe all notes from dao when observe all notes is called`() {
        every { noteDao.observeAllNotes() } returns flowOf(listOf(noteEntity))

        sut.observeAllNotes()

        verify { noteDao.observeAllNotes() }
    }

    @Test
    fun `should observe note by id from dao when observe note by id is called`() {
        every { noteDao.observeNoteById(ANY_ID) } returns flowOf(noteEntity)

        sut.observeNoteById(ANY_ID)

        verify { noteDao.observeNoteById(ANY_ID) }
    }

    @Test
    fun `should insert note into dao when insert note is called`() = runTest {
        everySuspend { noteDao.insertNote(noteEntity) } returns Unit

        sut.insertNote(noteEntity)

        verifySuspend { noteDao.insertNote(noteEntity) }
    }

    @Test
    fun `should delete note by id from dao when delete note by id is called`() = runTest {
        everySuspend { noteDao.deleteNoteById(ANY_ID) } returns Unit

        sut.deleteNoteById(ANY_ID)

        verifySuspend { noteDao.deleteNoteById(ANY_ID) }
    }

    private companion object {
        const val ANY_ID = 1

        val noteEntity = NoteEntity(
            id = ANY_ID,
            title = "title",
            description = "description"
        )
    }
}
