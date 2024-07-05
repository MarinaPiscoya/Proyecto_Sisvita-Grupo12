package com.example.sysvita.data

data class TestResult(
    val cuestionario: String,
    val diagnostico: String,
    val fecha: String,
    val id_cuest_det: Int,
    val nivel: String,
    val nom_completo: String,
    val punt_total: Int
)
