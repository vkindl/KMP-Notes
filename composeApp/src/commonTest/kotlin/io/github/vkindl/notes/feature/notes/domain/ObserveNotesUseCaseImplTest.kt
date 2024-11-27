package io.github.vkindl.notes.feature.notes.domain

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import io.github.vkindl.notes.core.domain.Note
import io.github.vkindl.notes.core.domain.NoteRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ObserveNotesUseCaseImplTest {

    private lateinit var noteRepository: NoteRepository
    private lateinit var sut: ObserveNotesUseCaseImpl

    @BeforeTest
    fun setUp() {
        noteRepository = mock<NoteRepository>()

        sut = ObserveNotesUseCaseImpl(
            noteRepository = noteRepository
        )
    }

    @Test
    fun `should emit all notes on invoke`() = runTest {
        every { noteRepository.observeAllNotes() } returns flowOf(notes)

        val result = sut.invoke().first()

        verify { noteRepository.observeAllNotes() }
        assertEquals(notes, result)
    }

    private companion object {
        val note1 = Note(
            id = 1,
            title = "title",
            description = "description"
        )
        val note2 = Note(
            id = 2,
            title = "title",
            description = "description"
        )
        val note3 = Note(
            id = 3,
            title = "title",
            description = "description"
        )
        val notes = listOf(
            note1,
            note2,
            note3
        )
    }
}
