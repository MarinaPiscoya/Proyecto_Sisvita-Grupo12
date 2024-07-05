package com.example.sysvita.viewModel

import com.example.sysvita.data.PersonaDiagResponse
import com.example.sysvita.network.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Response

class PacienteRepository {

    private val sisvitaService = RetrofitInstance.api

    suspend fun findPersonaDiag(fecha: String?, idTipTest: Int?, idNivel: Int?): Response<PersonaDiagResponse> {
        return try {
            sisvitaService.findPersonaDiag(fecha, idTipTest, idNivel)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.error(500, ResponseBody.create(null, ""))
        }
    }
}