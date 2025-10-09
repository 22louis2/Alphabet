package com.example.myapplication.data

class FakeRepository(
    alphabets: List<Pair<Char, String>> = listOf(
        'A' to "Apple",
        'B' to "Ball",
        'C' to "Cat"
    )
) : AlphabetRepository by AlphabetRepositoryImpl(alphabets)