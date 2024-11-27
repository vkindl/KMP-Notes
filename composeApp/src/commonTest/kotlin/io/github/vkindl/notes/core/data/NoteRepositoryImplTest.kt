package io.github.vkindl.notes.core.data

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verifySuspend
import io.github.vkindl.notes.core.data.model.NoteEntity
import io.github.vkindl.notes.core.data.source.NoteLocalDataSource
import io.github.vkindl.notes.core.domain.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NoteRepositoryImplTest {

    private lateinit var noteLocalDataSource: NoteLocalDataSource
    private lateinit var sut: NoteRepositoryImpl

    @BeforeTest
    fun setUp() {
        noteLocalDataSource = mock<NoteLocalDataSource>()

        sut = NoteRepositoryImpl(
            noteLocalDataSource = noteLocalDataSource
        )
    }

    @Test
    fun `should observe all notes from local data source when observe all notes is called`() {
        runTest {
            every { noteLocalDataSource.observeAllNotes() } returns flowOf(listOf(noteEntity))

            val result = sut.observeAllNotes().first()

            verify { noteLocalDataSource.observeAllNotes() }
            assertEquals(listOf(note), result)
        }
    }

    @Test
    fun `should observe note by id from local data source when observe note by id is called`() {
        runTest {
            every { noteLocalDataSource.observeNoteById(ANY_ID) } returns flowOf(noteEntity)

            val result = sut.observeNoteById(ANY_ID).first()

            verify { noteLocalDataSource.observeNoteById(ANY_ID) }
            assertEquals(note, result)
        }
    }

    @Test
    fun `should insert note into local data source when insert note is called`() = runTest {
        everySuspend { noteLocalDataSource.insertNote(noteEntity) } returns Unit

        sut.insertNote(note)

        verifySuspend { noteLocalDataSource.insertNote(noteEntity) }
    }

    @Test
    fun `should delete note by id from local data source when delete note by id is called`() {
        runTest {
            everySuspend { noteLocalDataSource.deleteNoteById(ANY_ID) } returns Unit

            sut.deleteNoteById(ANY_ID)

            verifySuspend { noteLocalDataSource.deleteNoteById(ANY_ID) }
        }
    }

    private companion object {
        const val ANY_ID = 1

        val note = Note(
            id = ANY_ID,
            title = "title",
            description = "description"
        )

        val noteEntity = NoteEntity(
            id = ANY_ID,
            title = "title",
            description = "description"
        )
    }
}
