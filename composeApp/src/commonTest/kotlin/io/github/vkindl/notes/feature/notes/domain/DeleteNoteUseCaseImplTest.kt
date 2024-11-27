package io.github.vkindl.notes.feature.notes.domain

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import io.github.vkindl.notes.core.domain.NoteRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class DeleteNoteUseCaseImplTest {

    private lateinit var noteRepository: NoteRepository
    private lateinit var sut: DeleteNoteUseCaseImpl

    @BeforeTest
    fun setUp() {
        noteRepository = mock<NoteRepository>()

        sut = DeleteNoteUseCaseImpl(
            noteRepository = noteRepository
        )
    }

    @Test
    fun `should delete note by id on invoke`() = runTest {
        everySuspend { noteRepository.deleteNoteById(id = ANY_ID) } returns Unit

        sut.invoke(id = ANY_ID)

        verifySuspend { noteRepository.deleteNoteById(id = ANY_ID) }
    }

    private companion object {
        const val ANY_ID = 1
    }
}
