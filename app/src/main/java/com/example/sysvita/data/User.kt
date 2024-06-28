package com.example.sysvita.data

data class User(
    val email: String,
    val direccion: String? = null,
    val password: String,
    val username: String? = null
)
