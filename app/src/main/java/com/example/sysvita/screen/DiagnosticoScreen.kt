package com.example.sysvita.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sysvita.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiagnosticoScreen(navController: NavHostController, idCuestDet:Int?, nomCompleto:String?, cuestionario:String?, puntTotal:Int?, nivel:String?) {
    var diagnostico by remember { mutableStateOf("Seleccione") }
    var fundamentoCientifico by remember { mutableStateOf("") }
    var tratamiento by remember { mutableStateOf("Seleccione") }
    var tratamientosAgregados by remember { mutableStateOf("") }
    var comunicacion by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Botón de regreso
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.regresar),
                        contentDescription = "Regresar",
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = "Regresar",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        item {
            // Datos del paciente
            SectionBox(title = "Datos del Paciente"
                        ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Apellidos y Nombres: ${nomCompleto}")
                    Text("Tipo Test: ${cuestionario}")
                    Text("Score: ${puntTotal}")
                    Text("Nivel: ${nivel}")
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            // Evaluación Especialista
            SectionBox(title = "Evaluación Especialista") {
                Column(modifier = Modifier.padding(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Diagnóstico:")
                        Spacer(modifier = Modifier.width(8.dp))
                        // Combo para Diagnóstico
                        var expanded by remember { mutableStateOf(false) }
                        Box(
                            modifier = Modifier
                                .clickable { expanded = true }
                                .background(Color.White)
                                .padding(dimensionResource(R.dimen.padding_small))
                                .border(1.dp, Color.Gray)
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    diagnostico,
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.down),
                                    contentDescription = "Expandir",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                                DropdownMenuItem(
                                    text = { Text("Diagnóstico 1") },
                                    onClick = {
                                        diagnostico = "Diagnóstico 1"
                                        expanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Diagnóstico 2") },
                                    onClick = {
                                        diagnostico = "Diagnóstico 2"
                                        expanded = false
                                    }
                                )
                                // Agregar más diagnósticos aquí
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = { /* Acción para agregar nuevo diagnóstico */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.down),
                                contentDescription = "Expandir",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Fundamento Científico:")
                    BasicTextField(
                        value = fundamentoCientifico,
                        onValueChange = { fundamentoCientifico = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.White)
                            .padding(dimensionResource(R.dimen.padding_small))
                            .border(1.dp, Color.Gray),
                        textStyle = TextStyle(color = Color.Black)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            // Del tratamiento
            SectionBox(title = "Del tratamiento") {
                Column(modifier = Modifier.padding(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Tratamiento:")
                        Spacer(modifier = Modifier.width(8.dp))
                        // Combo para Tratamiento
                        var expandedTratamiento by remember { mutableStateOf(false) }
                        Box(
                            modifier = Modifier
                                .clickable { expandedTratamiento = true }
                                .background(Color.White)
                                .padding(dimensionResource(R.dimen.padding_small))
                                .border(1.dp, Color.Gray)
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    tratamiento,
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.down),
                                    contentDescription = "Expandir",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            DropdownMenu(expanded = expandedTratamiento, onDismissRequest = { expandedTratamiento = false }) {
                                DropdownMenuItem(
                                    text = { Text("Tratamiento 1") },
                                    onClick = {
                                        tratamiento = "Tratamiento 1"
                                        expandedTratamiento = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Tratamiento 2") },
                                    onClick = {
                                        tratamiento = "Tratamiento 2"
                                        expandedTratamiento = false
                                    }
                                )
                                // Agregar más tratamientos aquí
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = { /* Acción para buscar tratamiento */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.local_hospita),
                                contentDescription = "Buscar Tratamiento"
                            )
                        }
                        IconButton(onClick = { /* Acción para agregar nuevo tratamiento */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.local_hospita),
                                contentDescription = "Agregar Tratamiento"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Tratamientos Agregados:")
                    BasicTextField(
                        value = tratamientosAgregados,
                        onValueChange = { tratamientosAgregados = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.White)
                            .padding(8.dp)
                            .border(1.dp, Color.Gray),
                        textStyle = TextStyle(color = Color.Black)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            // De la comunicación
            SectionBox(title = "De la comunicación") {
                Column(modifier = Modifier.padding(8.dp)) {
                    BasicTextField(
                        value = comunicacion,
                        onValueChange = { comunicacion = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.White)
                            .padding(dimensionResource(R.dimen.padding_small))
                            .border(1.dp, Color.Gray),
                        textStyle = TextStyle(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        Button(onClick = {  }) {
                            Text("Registrar")
                        }
                        Button(onClick = {  }) {
                            Text("Notificar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionBox(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(title, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}
