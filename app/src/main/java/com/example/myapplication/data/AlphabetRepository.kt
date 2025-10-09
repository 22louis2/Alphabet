package com.example.myapplication.data

interface AlphabetRepository {
    fun getAlphabetData(): List<Pair<Char, String>>
    suspend fun getNextAlphabet(currentAlphabet: Pair<Char, String>): Pair<Char, String>
}