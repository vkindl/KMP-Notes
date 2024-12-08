package io.github.vkindl.notes.feature.notes.presentation

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verifySuspend
import io.github.vkindl.notes.core.domain.Note
import io.github.vkindl.notes.core.domain.SelectedNoteRepository
import io.github.vkindl.notes.feature.notes.domain.DeleteNoteUseCase
import io.github.vkindl.notes.feature.notes.domain.ObserveNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NotesViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var deleteNoteUseCase: DeleteNoteUseCase
    private lateinit var observeNotesUseCase: ObserveNotesUseCase
    private lateinit var selectedNoteRepository: SelectedNoteRepository
    private lateinit var sut: NotesViewModel

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        deleteNoteUseCase = mock {
            everySuspend { invoke(ANY_ID) } returns Unit
        }
        observeNotesUseCase = mock {
            every { invoke() } returns flowOf(listOf(note))
        }
        selectedNoteRepository = mock {
            every { getNoteId() } returns null
            every { setNoteId(any()) } returns Unit
        }

        sut = NotesViewModel(
            deleteNoteUseCase = deleteNoteUseCase,
            observeNotesUseCase = observeNotesUseCase,
            selectedNoteRepository = selectedNoteRepository
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should observe notes on init`() = runTest {
        val result = sut.state.first()

        assertEquals(listOf(note), (result as NotesViewModel.NotesUiState.Notes).notes)
    }

    @Test
    fun `should delete note on delete note`() {
        sut.deleteNote(ANY_ID)

        verifySuspend { deleteNoteUseCase(ANY_ID) }
    }

    @Test
    fun `should set selected note id`() {
        sut.setSelectedNoteId(ANY_ID)

        verify { selectedNoteRepository.setNoteId(ANY_ID) }
    }

    private companion object {
        const val ANY_ID = 1
        val note = Note(
            id = ANY_ID,
            title = "title",
            description = "description"
        )
    }
}
