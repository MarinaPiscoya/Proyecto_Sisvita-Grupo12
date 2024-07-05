package com.example.sysvita.viewModel

import androidx.lifecycle.ViewModel
import com.example.sysvita.data.PersonaDiagResponse
import com.example.sysvita.data.TestResult
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class PacienteViewModel:ViewModel() {
    private val pacienteRepository = PacienteRepository()

    var testResults by mutableStateOf<List<TestResult>>(emptyList())
        private set

    fun getPersonaDiag(fecha: String?, idTipTest: Int?, idNivel: Int?) {
        viewModelScope.launch {
            val response: Response<PersonaDiagResponse> = pacienteRepository.findPersonaDiag(fecha, idTipTest, idNivel)
            if (response.isSuccessful) {
                testResults = response.body()?.data ?: emptyList()
            } else {
                //error
                testResults = emptyList()
            }
        }
    }
}