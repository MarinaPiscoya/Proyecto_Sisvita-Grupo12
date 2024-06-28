package com.example.sysvita.network

import com.example.sysvita.data.ApiResponse
import com.example.sysvita.data.RegisterRequest
import com.example.sysvita.data.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SisvitaService {

    @POST("/registrar")
    suspend fun registerUser(@Body user: RegisterRequest): Response<Unit>

    @POST("/iniciarSesion")
    suspend fun loginUser(@Body user: LoginRequest): Response<ApiResponse>
}










