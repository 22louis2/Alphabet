package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AlphabetData
import com.example.myapplication.data.AlphabetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlphabetViewModel @Inject constructor(
    private val alphabetRepository: AlphabetRepository,
): ViewModel() {
    // private val alphabetData = AlphabetData.alphabetData
    private val alphabetData = alphabetRepository.getAlphabetData()

    // create MutableStateFlow
    private val _alphabetUiState = MutableStateFlow(AlphabetUiState(
        alphabet = alphabetData[0].first,
        word = alphabetData[0].second
    ))

    // expose it as read only state
    val alphabetUiState = _alphabetUiState.asStateFlow()

    fun nextAlphabet() {
        viewModelScope.launch {
            // set loading as true
            _alphabetUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            val nextAlphabet = alphabetRepository.getNextAlphabet(_alphabetUiState.value.alphabet to _alphabetUiState.value.word)

            if(nextAlphabet == alphabetData.last()){
                _alphabetUiState.update {
                    it.copy(
                        alphabet = nextAlphabet.first,
                        word = nextAlphabet.second,
                        isCompleted = true,
                        isLoading = false
                    )
                }
            }
            else {
                _alphabetUiState.update {
                    it.copy(
                        alphabet = nextAlphabet.first,
                        word = nextAlphabet.second,
                        isCompleted = false,
                        isLoading = false
                    )
                }
            }
        }

        // val currentIndex = alphabetData.indexOfFirst { it.first == _alphabetUiState.value.alphabets }
//        val currentIndex = alphabetData.indexOf(_alphabetUiState.value.alphabet to _alphabetUiState.value.word)
//        if(currentIndex < alphabetData.size - 1){ // up to current index = 24
//            _alphabetUiState.update {
//                it.copy(
//                    alphabet = alphabetData[currentIndex + 1].first,
//                    word = alphabetData[currentIndex + 1].second
//                )
//            }
//        }
//        else {
//            _alphabetUiState.update {
//                it.copy(
//                    alphabet = alphabetData[0].first,
//                    word = alphabetData[0].second
//                )
//            }
//        }
//
//        if(currentIndex == alphabetData.size - 2){
//            _alphabetUiState.update {
//                it.copy(
//                    isCompleted = true
//                )
//            }
//        }
    }
}