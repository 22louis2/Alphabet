package com.example.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.AlphabetRepositoryImpl
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun AlphabetExpScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            // Get view model instance
            // val alphabetViewModel: AlphabetViewModel = viewModel()
            val alphabetViewModel: AlphabetViewModel = hiltViewModel()

            val alphabetUiState = alphabetViewModel.alphabetUiState.collectAsState()

            Text(text = "${alphabetUiState.value.alphabet} : ${alphabetUiState.value.word}")
            Button(
                onClick = {
                    alphabetViewModel.nextAlphabet()
                },
            ) {
                Text(text = "Next")
            }

            when {
                alphabetUiState.value.isCompleted -> {
                    Text(text = "Completed")
                }
                alphabetUiState.value.isLoading -> {
                    LinearProgressIndicator()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AlphabetExpScreenPreview() {
    MyApplicationTheme {
        AlphabetExpScreen()
    }
}