package com.example.myapplication.ui

data class AlphabetUiState(
    val alphabet: Char,
    val word: String,
    val isCompleted: Boolean = false,
    // val isLoading: Boolean = false,
    // val isError: String? = null,
)
