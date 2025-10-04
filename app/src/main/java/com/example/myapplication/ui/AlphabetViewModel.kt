package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.AlphabetData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AlphabetViewModel: ViewModel() {
    private val alphabetData = AlphabetData.alphabetData

    // create MutableStateFlow
    private val _alphabetUiState = MutableStateFlow(AlphabetUiState(
        alphabet = alphabetData[0].first,
        word = alphabetData[0].second
    ))

    // expose it as read only state
    val alphabetUiState = _alphabetUiState.asStateFlow()

    fun nextAlphabet() {
        // val currentIndex = alphabetData.indexOfFirst { it.first == _alphabetUiState.value.alphabets }
        val currentIndex = alphabetData.indexOf(_alphabetUiState.value.alphabet to _alphabetUiState.value.word)
        if(currentIndex < alphabetData.size - 1){ // up to current index = 24
            _alphabetUiState.update {
                it.copy(
                    alphabet = alphabetData[currentIndex + 1].first,
                    word = alphabetData[currentIndex + 1].second
                )
            }
        }
        else {
            _alphabetUiState.update {
                it.copy(
                    alphabet = alphabetData[0].first,
                    word = alphabetData[0].second
                )
            }
        }

        if(currentIndex == alphabetData.size - 2){
            _alphabetUiState.update {
                it.copy(
                    isCompleted = true
                )
            }
        }
    }
}