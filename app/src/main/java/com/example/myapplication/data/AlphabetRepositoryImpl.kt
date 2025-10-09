package com.example.myapplication.data

import kotlinx.coroutines.delay

class AlphabetRepositoryImpl(
    private val alphabets: List<Pair<Char, String>> = AlphabetData.alphabetData
): AlphabetRepository {
    override fun getAlphabetData(): List<Pair<Char, String>> = alphabets

    override suspend fun getNextAlphabet(currentAlphabet: Pair<Char, String>): Pair<Char, String> {
        val alphabetData = getAlphabetData()
        delay(2000)
        val currentIndex = alphabetData.indexOf(currentAlphabet)

        return if (currentIndex < alphabetData.size - 1) {
            alphabetData[currentIndex + 1]
        }
        else {
            alphabetData.first()
        }
    }
}