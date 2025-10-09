package com.example.myapplication.ui.test

import com.example.myapplication.data.FakeRepository
import com.example.myapplication.ui.AlphabetViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class AlphabetViewModelTest {
    private lateinit var alphabetViewModel: AlphabetViewModel
    private lateinit var testDispatcher: TestDispatcher

    @Before
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        alphabetViewModel = AlphabetViewModel(FakeRepository())
    }

    @Test
    fun testInitialAlphabet() {
        val uiState = alphabetViewModel.alphabetUiState.value
        assert(uiState.alphabet == 'A')
        assert(uiState.word == "Apple")
        assert(!uiState.isCompleted)
    }

    @Test
    fun testAlphabetUiStateAfterNextAlphabet() = runTest {
        alphabetViewModel.nextAlphabet()
        advanceUntilIdle()
//        runCurrent()
        val uiState = alphabetViewModel.alphabetUiState.value
        assert(uiState.alphabet == 'B')
        assert(uiState.word == "Ball")
        assert(!uiState.isCompleted)
    }

    @Test
    fun testAlphabetUiStateAfterNextAlphabetCompleted() = runTest {
        repeat(2) {
            alphabetViewModel.nextAlphabet()
            advanceUntilIdle()
        }
        val uiState = alphabetViewModel.alphabetUiState.value
        assert(uiState.alphabet == 'C')
        assert(uiState.word == "Cat")
        assert(uiState.isCompleted)
    }
}