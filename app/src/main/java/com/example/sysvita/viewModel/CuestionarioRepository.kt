package com.example.sysvita.viewModel

import com.example.sysvita.data.CuestionariosResponse
import com.example.sysvita.data.PersonaDiagResponse
import com.example.sysvita.network.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Response

class CuestionarioRepository {
    private val sisvitaService = RetrofitInstance.api

    suspend fun getCuestionarios(): Response<CuestionariosResponse> {
        return try {
            sisvitaService.findCuestionarios()
        } catch (e: Exception) {
            e.printStackTrace()
            Response.error(500, ResponseBody.create(null, ""))
        }
    }
}