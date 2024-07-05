package com.example.sysvita.screen

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sysvita.R
import com.example.sysvita.viewModel.CuestionarioViewModel
import com.example.sysvita.viewModel.PacienteViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GestionPacientesScreen(navController: NavHostController) {
    val pacienteViewModel: PacienteViewModel = viewModel()
    val cuestionarioViewModel: CuestionarioViewModel = viewModel()

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedTestType by remember { mutableStateOf("Todos") }
    var selectedNivel by remember { mutableStateOf("Todos") }
    var expanded by remember { mutableStateOf(false)}

    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val selectedDateString = selectedDate.format(dateFormatter)

    LaunchedEffect(Unit) {
        pacienteViewModel.getPersonaDiag(selectedDate.toString(), null, null)
        cuestionarioViewModel.getCuestionarios()
    }

    val testResults = pacienteViewModel.testResults
    val cuestionarios = cuestionarioViewModel.cuestionarioResult

    val context = LocalContext.current

    // Date picker dialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            pacienteViewModel.getPersonaDiag(selectedDate.format(dateFormatter), null, null)
        },
        selectedDate.year,
        selectedDate.monthValue - 1,
        selectedDate.dayOfMonth
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .padding(dimensionResource(R.dimen.padding_small)),
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.Realizar_Vigilancia),
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                ),
            )
        }


        Spacer(modifier = Modifier.height(16.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            // Filtro por fecha
            Text(text = stringResource(R.string.fecha), fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { datePickerDialog.show() }
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Icon(Icons.Default.Search, contentDescription = "Search Icon", modifier = Modifier.size(20.dp))
                Text(text = selectedDateString)

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            // Filtro por tipo de test
            Text(text = stringResource(R.string.tipo_test), fontWeight = FontWeight.Bold)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                var expandedTestType by remember { mutableStateOf(false) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { expandedTestType = true }
                        .padding(dimensionResource(R.dimen.padding_small))
                ) {
                    Text(
                        text = selectedTestType,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.down),
                        contentDescription = "Expandir",
                        modifier = Modifier.size(20.dp)
                    )
                }

                DropdownMenu(
                    expanded = expandedTestType,
                    onDismissRequest = { expandedTestType = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Todos") },
                        onClick = {
                            selectedTestType = "Todos"
                            expandedTestType = false
                            pacienteViewModel.getPersonaDiag(selectedDateString, null, null)
                        }
                    )
                    cuestionarios.forEach { cuestionario ->
                        DropdownMenuItem(
                            text = { Text(cuestionario.titulo) },
                            onClick = {
                                selectedTestType = cuestionario.titulo
                                expandedTestType = false
                                pacienteViewModel.getPersonaDiag(selectedDateString, null, null)
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            // Filtro por nivel
            Text(text = "Nivel", fontWeight = FontWeight.Bold)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                var expandedNivel by remember { mutableStateOf(false) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { expandedNivel = true }
                        .padding(dimensionResource(R.dimen.padding_small))
                ) {
                    Text(
                        text = selectedNivel,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.down),
                        contentDescription = "Expandir",
                        modifier = Modifier.size(20.dp)
                    )
                }

                val niveles = listOf("Todos", "bajo", "medio", "alto")

                DropdownMenu(
                    expanded = expandedNivel,
                    onDismissRequest = { expandedNivel = false }
                ) {
                    niveles.forEach { nivel ->
                        DropdownMenuItem(
                            text = { Text(nivel.capitalize()) },
                            onClick = {
                                selectedNivel = nivel
                                expandedNivel = false
                                pacienteViewModel.getPersonaDiag(selectedDateString, null, null)
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de resultados
        LazyColumn {
            items(testResults.filter {
                ((selectedTestType == "Todos" || it.cuestionario == selectedTestType) &&
                        (selectedNivel == "Todos" || it.nivel == selectedNivel) &&
                        (it.fecha == selectedDateString))
            }) { result ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${stringResource(R.string.paciente)}: ${result.nom_completo} ",fontWeight = FontWeight.Bold
                            )
                            Text(text = "Test: ${result.cuestionario}")
                            Text(text = "Puntaje: ${result.punt_total}")
                            Text(text = "Nivel: ${result.nivel}")
                            Text(text = "Fecha: ${result.fecha}")
                        }
                        val color = when (result.nivel) {
                            "bajo" -> Color.Green
                            "medio" -> Color.Yellow
                            "alto" -> Color.Red
                            else -> Color.Gray
                        }
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(color, shape = CircleShape)
                        )
                    }
                    Row {
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                navController.navigate("diagnostico/" +
                                        "${result.id_cuest_det}/" +
                                        "${result.nom_completo}/" +
                                        "${result.cuestionario}/" +
                                        "${result.punt_total}/" +
                                        "${result.nivel}")
                            }
                        ) {
                            Text(text = stringResource(R.string.realizar_diagnostico), color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

