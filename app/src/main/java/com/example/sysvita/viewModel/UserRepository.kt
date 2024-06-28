package com.example.sysvita.viewModel

import com.example.sysvita.data.ApiResponse
import com.example.sysvita.data.User
import com.example.sysvita.data.LoginRequest
import com.example.sysvita.data.RegisterRequest
import com.example.sysvita.network.RetrofitInstance


class UserRepository {

    private val sisvitaService = RetrofitInstance.api

    suspend fun registerUser(user: User): Boolean {
        return try {
            val request = RegisterRequest(
                nom_comp = user.username,
                direc = user.direccion,
                email = user.email,
                contra = user.password
            )
            val response = sisvitaService.registerUser(request)
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun loginUser(user: User): ApiResponse {
        return try {
            val request = LoginRequest(
                email = user.email,
                contra = user.password
            )
            val response = sisvitaService.loginUser(request)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                ApiResponse(null, "Error en el inicio de sesión", false)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResponse(null, "Error en la solicitud: ${e.message}", false)
        }
    }
}


