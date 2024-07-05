package com.example.sysvita.data

data class PersonaDiagResponse(
    val data: List<TestResult>,
    val success: Boolean,
    val message: String
)
