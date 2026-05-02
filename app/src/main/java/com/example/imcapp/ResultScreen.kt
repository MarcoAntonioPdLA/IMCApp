package com.example.imcapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.imcapp.ui.theme.Typography

data class IMCDiagnosis(
    val status: String,
    val diagnosis: String
)

@Composable
fun ResultScreen(navController: NavController, genderIndex: Int, height: Int, weight: Int, age: Int) {
    val imc: Float = calculateIMC(weight, height)
    val imcDiagnosis: IMCDiagnosis = getIMCDiagnosis(imc)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RESULTADOS",
            style = Typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "IMC = $imc",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Purple
        )
        Text(
            text = imcDiagnosis.status,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGreen
        )
        Text(
            text = imcDiagnosis.diagnosis,
            fontSize = 16.sp,
            color = Color.Black
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Routes.formScreen)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Purple)
        ) {
            Text(
                text = "RECALCULAR",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

fun calculateIMC(weight: Int, height: Int): Float {
    return weight.toFloat() / (height.toFloat() * height.toFloat() * 1/10000)
}

fun getIMCDiagnosis(imc: Float): IMCDiagnosis {
    return when(imc) {
        in 0f .. 18.5f -> IMCDiagnosis(status = "BAJO PESO", diagnosis = "Tu peso está por debajo de lo recomendado.")
        in 18.5f .. 25f -> IMCDiagnosis(status = "PESO NORMAL", diagnosis = "Tu peso se encuentra dentro de los valores normales.")
        in 25f..30f -> IMCDiagnosis(status = "SOBREPESO", diagnosis = "Tu peso está ligeramente por encima de lo recomendado.")
        in 30f..35f -> IMCDiagnosis(status = "OBESIDAD GRADO I", diagnosis = "Tu peso representa un riesgo moderado para tu salud.")
        in 35f..40f -> IMCDiagnosis(status = "OBESIDAD GRADO II", diagnosis = "Tu peso representa un riesgo alto para tu salud.")
        in 40f..Float.MAX_VALUE -> IMCDiagnosis(status = "OBESIDAD GRADO III", diagnosis = "Tu peso representa un riesgo muy alto para tu salud.")
        else -> IMCDiagnosis(status = "FUERA DE RANGO", diagnosis = "Hubo un error con los datos ingresados.")
    }
}