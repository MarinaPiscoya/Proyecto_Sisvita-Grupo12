package com.example.sysvita.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sysvita.data.Cuestionario
import com.example.sysvita.data.CuestionariosResponse
import com.example.sysvita.data.PersonaDiagResponse
import com.example.sysvita.data.TestResult
import kotlinx.coroutines.launch
import retrofit2.Response

class CuestionarioViewModel: ViewModel() {
    private val cuestionarioRepository = CuestionarioRepository()

    var cuestionarioResult by mutableStateOf<List<Cuestionario>>(emptyList())
        private set
    init {
        getCuestionarios()
    }
    fun getCuestionarios() {
        viewModelScope.launch {
            val response: Response<CuestionariosResponse> = cuestionarioRepository.getCuestionarios()
            if (response.isSuccessful) {
                cuestionarioResult = response.body()?.data ?: emptyList()
            } else {
                //error
                cuestionarioResult = emptyList()
            }
        }
    }
}